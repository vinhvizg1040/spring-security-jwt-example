/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services.implementations;

import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.entities.Account;
import com.example.demo.repo.AccountRepo;
import com.example.demo.services.interfaces.IAccount;
import com.example.demo.utils.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author PCvinhvizg
 */
@Service
public class AccountService implements IAccount {

    @Autowired
    private ModelMapperUtil mapper;

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public boolean registerAccount(RegisterRequest registerRequest) {
        Account account = mapper.map(registerRequest, Account.class);
        Account acc = accountRepo.findByUsername(account.getUsername());
        if (acc == null) {
            account.setPassword(encryPassword(account.getPassword()));
            account.setRoles("ROLE_USER");
            accountRepo.save(account);
            return true;
        } else {
            return false;
        }
    }

    public static String encryPassword(String pass) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(pass);
    }

    @Override
    public Account getAccount(String username) {
        return accountRepo.findByUsername(username);
    }
    
    
}
