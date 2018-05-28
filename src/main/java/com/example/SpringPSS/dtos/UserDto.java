package com.example.SpringPSS.dtos;

import com.example.SpringPSS.entities.Role;
import com.example.SpringPSS.security.PasswordMatches;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@PasswordMatches
public class UserDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    private String matchingPassword;

    @NotEmpty
    private String nip;

    @NotEmpty
    private String companyName;

    @NotEmpty
    private List<Role> roles;

    @NotEmpty
    private Boolean enabled;

    public UserDto() {

    }

    public UserDto(@NotEmpty String username, @NotEmpty String password, String matchingPassword,
                   @NotEmpty String nip, @NotEmpty String companyName,
                   @NotEmpty List<Role> roles, @NotEmpty Boolean enabled) {
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.nip = nip;
        this.companyName = companyName;
        this.roles = roles;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
