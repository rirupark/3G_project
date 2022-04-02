package com.example.bottom_navigation;

public class UserLearn {
    private String className;
    private String tongArea;


    private int credit;
    private String area;

    public UserLearn() {}
    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTongArea() {return tongArea;}

    public void setTongArea(String tongArea) { this.tongArea = tongArea; }




    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}