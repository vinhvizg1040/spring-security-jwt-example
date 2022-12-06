/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.dto.response;

import com.example.demo.dto.enums.Roles;
import java.util.Date;

/**
 *
 * @author PCvinhvizg
 */
public class LoginResponse {

    private String username;
    private String firstname;
    private String lastname;
    private String location;
    private String gender;
    private Date birthday;
    private String email;
    private String token;
    private Roles roles;

    public LoginResponse() {
    }

    public LoginResponse(String username, String firstname, String lastname, String location, String gender, Date birthday, String email, String token, Roles roles) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.location = location;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.token = token;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

}
