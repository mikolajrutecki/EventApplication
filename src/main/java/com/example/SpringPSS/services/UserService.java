package com.example.SpringPSS.services;

import com.example.SpringPSS.dtos.UserDto;
import com.example.SpringPSS.entities.User;
import com.example.SpringPSS.repositories.RoleRepository;
import com.example.SpringPSS.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public User registerNewUserAccount(UserDto userDto) throws Exception {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new Exception("There is an account with that username: " + userDto.getUsername());
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setNip(userDto.getNip());
        user.setCompanyName(userDto.getCompanyName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(userDto.getRoles());
        return userRepository.save(user);
    }

}
