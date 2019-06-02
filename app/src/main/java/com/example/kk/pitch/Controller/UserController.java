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

    public void showData(DataSnapshot dataSnapshot, UserInfo uInfo){

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String userID = user.getUid();

        for(DataSnapshot ds : dataSnapshot.getChildren()) {
            uInfo.setName(ds.child(userID).getValue(UserInfo.class).getName());                     //set name
            //uInfo.setEmail(ds.child(userID).getValue(UserInfo.class).getEmail());                   //set email
        }

    }
    public void addInfo(String email, String name){
        Log.e("I", "entered addinfo");
        if(email == null || email.equals("")){
            ((RegisterActivity)context).inputError("email");
        }else if(name == null || name.equals("")){
            ((RegisterActivity)context).inputError("name");
        }else{
            UserModel userModel = new UserModel();
            userModel.addNameToDB(name);
        }

    }

    public void createGroup(){};

    public void addPicture(){};

    public void addMember(){};

    public void addDescription(){};

    public void createPaymentRequest(){};



}
