/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.configuration.MyUserDetailsService;
import com.example.demo.dto.enums.Roles;
import com.example.demo.dto.request.AuthenticationRequest;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.LoginResponse;
import com.example.demo.entities.Account;
import com.example.demo.entities.Profile;
import com.example.demo.services.implementations.AccountService;
import com.example.demo.services.implementations.ProfileService;
import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author PCvinhvizg
 */
@RestController
public class HomeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ModelMapperUtil mapper;

    @GetMapping("/")
    public String home() {
        return ("<h1>Wellcome</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Wellcome Admin</h1>");
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Wellcome user</h1>");
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (AuthenticationException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        try {
            final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtUtils.generrateToken(userDetails);

            Profile profile = profileService.getProfileByUsername(authenticationRequest.getUsername());
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setBirthday(profile.getBirthday());
            loginResponse.setEmail(profile.getEmail());
            loginResponse.setFirstname(profile.getFirstname());
            loginResponse.setGender(profile.getGender());
            loginResponse.setLastname(profile.getLastname());
            loginResponse.setLocation(profile.getLocation());
            loginResponse.setUsername(profile.getUsername());

            loginResponse.setToken(jwt);
            Object[] role = userDetails.getAuthorities().toArray();
            loginResponse.setRoles(Roles.valueOf(role[0].toString()));

            return ResponseEntity.ok(loginResponse);
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            boolean result = accountService.registerAccount(registerRequest);
            if (result == true) {
                Profile profile = profileService.createProfile(registerRequest);

                final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(registerRequest.getUsername());
                final String jwt = jwtUtils.generrateToken(userDetails);
                LoginResponse loginResponse = new LoginResponse();

                loginResponse.setBirthday(profile.getBirthday());
                loginResponse.setEmail(profile.getEmail());
                loginResponse.setFirstname(profile.getFirstname());
                loginResponse.setGender(profile.getGender());
                loginResponse.setLastname(profile.getLastname());
                loginResponse.setLocation(profile.getLocation());
                loginResponse.setUsername(profile.getUsername());

                Object[] role = userDetails.getAuthorities().toArray();
                loginResponse.setRoles(Roles.valueOf(role[0].toString()));
                loginResponse.setToken(jwt);

                return ResponseEntity.ok(loginResponse);
            } else {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        } catch (UsernameNotFoundException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }
    }
}
