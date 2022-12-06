/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services.interfaces;

import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.entities.Profile;

/**
 *
 * @author PCvinhvizg
 */
public interface IProfile {

    Profile createProfile(RegisterRequest profile);
    Profile getProfileByUsername(String username);
}
