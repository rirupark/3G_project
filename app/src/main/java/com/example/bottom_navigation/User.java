package com.example.bottom_navigation;

public class User {
    private String name;


    public String username;
    public String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
