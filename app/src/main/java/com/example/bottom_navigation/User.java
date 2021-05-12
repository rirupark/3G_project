package com.example.bottom_navigation;
import com.google.firebase.database.IgnoreExtraProperties;
public class User {
    boolean isSelected;
    private String name;
    public int id;
    public String area;
    public int credit;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {     this.area = area;    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
