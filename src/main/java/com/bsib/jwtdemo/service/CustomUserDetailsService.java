package com.bsib.jwtdemo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    // this method actually does the validation for user existence
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("blessed")) { // here you can make a DB call with the help of a repository
            // and validate it
            return new User("blessed", "1234pass", new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User does not exist");
        }
    }
}
