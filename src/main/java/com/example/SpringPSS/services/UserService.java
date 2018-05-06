package com.example.SpringPSS.services;

import com.example.SpringPSS.dtos.UserDto;
import com.example.SpringPSS.entities.User;
import com.example.SpringPSS.repositories.RoleRepository;
import com.example.SpringPSS.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public User registerNewUserAccount(UserDto accountDto) throws Exception {
        if (userRepository.existsByUsername(accountDto.getUsername())) {
            throw new Exception("There is an account with that username: " + accountDto.getUsername());
        }
        User user = new User();
        user.setUsername(accountDto.getUsername());
        user.setNip(accountDto.getNip());
        user.setCompanyName(accountDto.getCompanyName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

}
