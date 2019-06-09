package com.example.kk.pitch.Model;

import android.os.Parcel;
import android.os.Parcelable;

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


    public GroupObject(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
