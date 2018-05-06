package com.example.SpringPSS.dtos;

import com.example.SpringPSS.security.PasswordMatches;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class UserDto {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    private String matchingPassword;

    @NotNull
    @NotEmpty
    private String nip;

    @NotNull
    @NotEmpty
    private String companyName;

    public UserDto() {

    }

    public UserDto(@NotNull @NotEmpty String username, @NotNull @NotEmpty String password, String matchingPassword,
                   @NotNull @NotEmpty String nip, @NotNull @NotEmpty String companyName) {
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.nip = nip;
        this.companyName = companyName;
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
}
