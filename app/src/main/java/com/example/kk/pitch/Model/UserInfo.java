package com.example.kk.pitch.Model;

public class UserInfo {

    private String name;
    private String username;

    private UserInfo(){}

    public static UserInfo instance;

    public static UserInfo getInstance(){
        if(instance == null){
            instance = new UserInfo();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
