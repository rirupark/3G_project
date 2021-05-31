package com.example.bottom_navigation;

public class DataStored {
    private String name;
    private String className;
    private String tongArea;
    private int credit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setTongArea(String tongArea) {
        this.tongArea = tongArea;
    }

    public int getCredit() {
        return credit;
    }

    public String getClassName() {
        return className;
    }

    public String getTongArea() {
        return tongArea;
    }
}
