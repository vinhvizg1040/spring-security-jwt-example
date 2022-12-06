/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services.implementations;

import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.entities.Profile;
import com.example.demo.repo.ProfileRepo;
import com.example.demo.services.interfaces.IProfile;
import com.example.demo.utils.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PCvinhvizg
 */
@Service
public class ProfileService implements IProfile {

    @Autowired
    private ModelMapperUtil mapper;

    @Autowired
    private ProfileRepo profileRepo;

    @Override
    public Profile createProfile(RegisterRequest registerRequest) {
        Profile profile = mapper.map(registerRequest, Profile.class);
        profileRepo.save(profile);
        return profile;
    }

    @Override
    public Profile getProfileByUsername(String username) {
        return profileRepo.findByUsername(username);
    }

    
}
