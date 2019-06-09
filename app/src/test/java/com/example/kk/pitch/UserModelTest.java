package com.example.kk.pitch;

import com.example.kk.pitch.Model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
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

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class UserModelTest {


    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static FirebaseDatabase db = FirebaseDatabase.getInstance();
    public static DatabaseReference myRef = db.getReference();
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    private FirebaseUser user;


   // @BeforeClass
    //public static void runBefore() {
        //mAuth = FirebaseAuth.getInstance();
      //  db = FirebaseDatabase.getInstance();
       // myRef = db.getReference();
   // }

    @Test
    public void mAuthTest() throws Exception{
        //FirebaseAuth fa = FirebaseAuth.getInstance();
        assertTrue(mAuth instanceof FirebaseAuth);
    }

    @Test
    public void dataBaseTest() throws Exception{
        //FirebaseDatabase db = FirebaseDatabase.getInstance();
        assertTrue(db instanceof FirebaseDatabase);
    }

        @Test
    public void myRefTest() throws Exception{
        //myRef = db.getReference();
        assertTrue(myRef instanceof DatabaseReference);
    }



}
