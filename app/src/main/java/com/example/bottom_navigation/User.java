package com.example.bottom_navigation;

public class User {
    boolean isSelected;
    private String name;
    public String username;
    public String email;


    public boolean getSelected(){
        return isSelected;
    }

    public void setSelected(boolean selected){
        isSelected = selected;
    }


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
