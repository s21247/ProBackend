package com.example.probackend.registration;

import java.util.Objects;


public class RegistrationRequest {
    private final String username;
    private final String email;
    private final String password;

    public RegistrationRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationRequest that = (RegistrationRequest) o;
        return username.equals(that.username) && email.equals(that.email) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username , email, password);
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "firstName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
