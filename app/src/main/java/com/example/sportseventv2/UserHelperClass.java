package com.example.sportseventv2;

public class UserHelperClass {
    String name, emial, phoneNo, username, password, events;

    public UserHelperClass(String name, String emial, String phoneNo, String username, String password, String events) {
        this.name = name;
        this.emial = emial;
        this.phoneNo = phoneNo;
        this.username = username;
        this.password = password;
        this.events = events;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
