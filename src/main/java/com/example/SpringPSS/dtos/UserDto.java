package com.example.SpringPSS.dtos;

import com.example.SpringPSS.entities.Role;
import com.example.SpringPSS.security.PasswordMatches;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;
@Getter
@Setter
@PasswordMatches
public class UserDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    private String matchingPassword;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String email;

    private List<Role> roles;

    private Boolean enabled;

    public UserDto(){

    }

    public UserDto(String username, String password, String firstName, String lastName, String email, List<Role> roles, Boolean enabled){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
        this.enabled = enabled;
    }


}
