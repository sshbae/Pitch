 package com.example.kk.pitch.Model;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentModel {

    private String name;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userID;
    private FirebaseUser user;

    public PaymentModel(){
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        user = mAuth.getCurrentUser();
        userID = user.getUid();
    }

    public  void addPaymentToDB(String paymentId, String senderId, String recipientId, String amount, String description,
                                String groupId){
        // Write a message to the database
        Log.e("NAME", "is added to DB");
        myRef.child("payments").push().setValue(paymentId);
        //    myRef.child("payments").child(paymentId).push().setValue("senderID");
        myRef.child("payments").child(paymentId).child("senderID").setValue(senderId);
        //   myRef.child("payments").child(paymentId).push().setValue("recipientId");
        myRef.child("payments").child(paymentId).child("recipientId").setValue(recipientId);
        //   myRef.child("payments").child(paymentId).push().setValue("amount");
        myRef.child("payments").child(paymentId).child("amount").setValue(amount);
        //   myRef.child("payments").child(paymentId).push().setValue("description");
        myRef.child("payments").child(paymentId).child("description").setValue(description);
        //    myRef.child("payments").child(paymentId).push().setValue("groupId");
        myRef.child("payments").child(paymentId).child("groupId").setValue(groupId);
    }

}
