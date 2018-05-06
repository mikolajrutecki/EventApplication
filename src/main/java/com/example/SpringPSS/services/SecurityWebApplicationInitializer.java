package com.example.SpringPSS.services;


import com.example.SpringPSS.dtos.UserDto;
import com.example.SpringPSS.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    @Autowired
    UserService userService;

    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
        try {
            userService.registerNewUserAccount(new UserDto("user", "password",
                    "password", 33344, "company"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}