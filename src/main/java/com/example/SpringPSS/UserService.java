package com.example.SpringPSS;

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
        user.setRoles(Collections.singletonList(roleRepository.findByName("USER")));
        return userRepository.save(user);
    }

}
