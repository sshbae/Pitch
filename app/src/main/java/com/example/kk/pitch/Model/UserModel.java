package com.example.kk.pitch.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;

public class UserModel{

    private String name;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    private FirebaseUser user;

    public UserModel(){
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        user = mAuth.getCurrentUser();
        userID = user.getUid();
    }

    public void addUserToDB(String name, String username){
        // Write a message to the database
        myRef.child("user_id").push().setValue(userID);
        myRef.child("user_id").child(userID).setValue(username);

        myRef.child("users").push().setValue(username);
        myRef.child("users").child(username).push().setValue("user_id");
        myRef.child("users").child(username).push().setValue("groups");
        myRef.child("users").child(username).child("user_id").setValue(userID);
        addNameToDB(name, username);
    }

    public void addNameToDB(String name, String username){
        myRef.child("users").child(username).push().setValue("name");
        myRef.child("users").child(username).child("name").setValue(name);
    }

    public void addGroupToUser(String groupID, String username){
        myRef.child("users").child(username).child("groups").push().setValue(groupID);
        myRef.child("users").child(username).child("groups").child(groupID).setValue("true");

    }

}
