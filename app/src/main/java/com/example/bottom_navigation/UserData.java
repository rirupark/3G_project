package com.example.bottom_navigation;

import android.graphics.drawable.Drawable;

import android.net.Uri;

public class UserData {
    boolean isSelected;
    private String userName;

    private String profile;


    public boolean getSelected(){
        return isSelected;
    }

    public void setSelected(boolean selected){
        isSelected = selected;
    }


    public UserData(String userName, String profile) {

        this.userName = userName;

        this.profile = profile;

    }


    public String getUserName() {

        return userName;

    }


    public void setUserName(String userName) {

        this.userName = userName;

    }


    public String getProfile() {

        return profile;

    }


    public void setProfile(String profile) {

        this.profile = profile;

    }



}

