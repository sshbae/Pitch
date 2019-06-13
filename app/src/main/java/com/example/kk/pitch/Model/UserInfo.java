package com.example.kk.pitch.Model;

import java.util.ArrayList;

public class UserInfo {

    private String name;
    private String username;
    private ArrayList<GroupObject> groups;

    private UserInfo(){
        groups = new ArrayList<>();
    }

    public static UserInfo instance;

    public ArrayList<GroupObject> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<GroupObject> groups) {
        this.groups = groups;
    }

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
