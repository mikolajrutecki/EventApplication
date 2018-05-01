package com.example.SpringPSS;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String login;
    private String password;
    private Integer nip;
    private Integer companyName;
    private String privilege; //zmien na enum

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getNip() {
        return nip;
    }

    public void setNip(Integer nip) {
        this.nip = nip;
    }

    public Integer getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Integer companyName) {
        this.companyName = companyName;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}
