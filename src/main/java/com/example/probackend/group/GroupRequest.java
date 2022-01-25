package com.example.probackend.group;

import com.example.probackend.User.AppUser;

public class GroupRequest {

    private final String name;


    public GroupRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
