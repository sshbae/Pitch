package com.example.kk.pitch.Model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GroupModel {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    private FirebaseUser user;

    public GroupModel(){
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        user = mAuth.getCurrentUser();
        userID = user.getUid();
    }

    public void addGroup(String groupname, String groupID){
        // Write a message to the database
        myRef.child("groups").push().setValue(groupID);
        myRef.child("groups").child(groupID).push().setValue("group_name");
        myRef.child("groups").child(groupID).child("group_name").setValue(groupname);
        myRef.child("groups").child(groupID).push().setValue("members");
    }

    public void addMembers(String name, String groupID){
        myRef.child("groups").child(groupID).child("members").push().setValue(name);
        myRef.child("groups").child(groupID).child("members").child(name).setValue("true");
    }
}
