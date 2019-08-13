/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thehumblefool.jwtdemo;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author shash
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo2 repo;

    @Override
    public UserDetails loadUserByUsername(String subject) throws UsernameNotFoundException {
        Optional<UserPersistenceEntityModel> user = repo.findByUsername(subject);
        if (user.isEmpty()) {
            user = repo.findByEmail(subject);
            if (user.isEmpty()) {
                throw new UsernameNotFoundException("user with username: " + subject + " not found.");
            }
            return new JwtUserDetailsImpl(user.get());
        }
        return new JwtUserDetailsImpl(user.get());
    }

    public UserDetails loadUserByUsernameOrEmail(String subject) throws UsernameNotFoundException {
        return loadUserByUsername(subject);
    }
}
