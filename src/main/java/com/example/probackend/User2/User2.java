package com.example.probackend.User2;

public class User2 {
    private final Integer userId;
    private final String userName;

    public User2(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
