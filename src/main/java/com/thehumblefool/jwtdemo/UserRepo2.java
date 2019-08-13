/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thehumblefool.jwtdemo;

import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shash
 */
@Repository
public class UserRepo2 {

    UserPersistenceEntityModel userPersistenceEntityModel;
    
    @Autowired
    PasswordEncoder encoder;

    @PostConstruct
    public void intUser() {
        userPersistenceEntityModel = new UserPersistenceEntityModel();
        userPersistenceEntityModel.setEmail("abc@xyz.com");
        userPersistenceEntityModel.setId(1);
        userPersistenceEntityModel.setUsername("abc");
        userPersistenceEntityModel.setPassword(encoder.encode("pass"));
    }

    Optional<UserPersistenceEntityModel> findByEmail(String email) {

        return Optional.of(userPersistenceEntityModel.getEmail().equals(email) ? userPersistenceEntityModel : null);
    }

    Optional<UserPersistenceEntityModel> findByUsername(String username) {
        return Optional.of(userPersistenceEntityModel.getUsername().equals(username) ? userPersistenceEntityModel : null);
    }
}
