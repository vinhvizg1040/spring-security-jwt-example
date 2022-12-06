/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repo;

import com.example.demo.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author PCvinhvizg
 */
public interface ProfileRepo extends JpaRepository<Profile, Integer> {

    Profile findByEmail(String email);

    @Query("SELECT p FROM Profile p WHERE p.username.username = :username")
    Profile findByUsername(@PathVariable("username") String username);
}
