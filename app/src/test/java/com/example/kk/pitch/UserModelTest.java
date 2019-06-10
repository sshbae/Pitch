package com.example.kk.pitch;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.Test;

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
    public void myRefTest() throws Exception {
            //myRef = db.getReference();
            assertTrue(myRef instanceof DatabaseReference);
    }
}
