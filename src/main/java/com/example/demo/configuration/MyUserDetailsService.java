/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.configuration;

import com.example.demo.entities.Account;
import com.example.demo.repo.AccountRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author PCvinhvizg
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username);
        if (account != null) {
            List<GrantedAuthority> authoritys = new ArrayList<>();
            authoritys.add(new SimpleGrantedAuthority(account.getRoles()));
            return (UserDetails) new User(account.getUsername(), account.getPassword(), authoritys);
        }
        throw new UsernameNotFoundException(username);
    }

    
}
