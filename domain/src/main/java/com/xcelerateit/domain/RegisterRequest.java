package com.xcelerateit.domain;

public class RegisterRequest {
    public String name;
    public String email;
    public String password;
    public String role;

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ",role='" +role + '\''+
                '}';
    }
}
