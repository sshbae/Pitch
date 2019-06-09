package com.example.kk.pitch.Controller;

import android.app.Activity;
import android.util.Log;

import com.example.kk.pitch.Model.UserInfo;
import com.example.kk.pitch.Model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

public class UserController {

    private Activity context;

    public UserController(Activity context){
        this.context = context;
    }


    public void addInfo(String username, String name){
        if(username == null || username.equals("")){
            ((RegisterInfoActivity)context).inputError("username");
        }else if(name == null || name.equals("")){
            ((RegisterInfoActivity)context).inputError("name");
        }else{
            UserModel userModel = new UserModel();
            userModel.addUserToDB(name, username);
        }

    }

    public void createGroup(){};

    public void addPicture(){};

    public void addMember(){};

    public void addDescription(){};

    public void createPaymentRequest(){};



}
