package com.example.SpringPSS.dtos;

import com.example.SpringPSS.entities.User;

import java.util.ArrayList;

public class UsersWrapper {
    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
