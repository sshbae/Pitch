package com.example.kk.pitch.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

public class GroupObject implements Parcelable{

    protected GroupObject(Parcel in) {
        name = in.readString();
    }

    public static final Creator<GroupObject> CREATOR = new Creator<GroupObject>() {
        @Override
        public GroupObject createFromParcel(Parcel in) {
            return new GroupObject(in);
        }

        @Override
        public GroupObject[] newArray(int size) {
            return new GroupObject[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }



    private String name;
    private String uniqueId;
    private HashMap<String, String> members;

    public GroupObject(String name){
        this.members = new HashMap<>();
        this.name = name;
        this.uniqueId = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, String> getMembers(){
        return members;
    }

    public void addMembers(String name, String username){
        members.put(name, username);
    }

    public String getUniqueId(){
        return uniqueId;
    }

    public void setUniqueId(String s){
        uniqueId = s;
    }

    public static String RandomKey(int n){
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}


