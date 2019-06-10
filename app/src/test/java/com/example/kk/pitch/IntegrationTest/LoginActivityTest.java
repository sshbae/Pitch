package com.example.kk.pitch.IntegrationTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kk.pitch.Controller.LoginActivity;
import com.example.kk.pitch.Model.LoginModel;
import com.example.kk.pitch.Model.UserModel;
import com.example.kk.pitch.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.kk.pitch.Model.LoginModel;
import com.example.kk.pitch.Model.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import static android.support.constraint.Constraints.TAG;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LoginActivityTest {
    public static FirebaseAuth mAuth;



    public static void main(String[] args)
    {

        System.out.println("Running Login Activity Testing..");
        //init
        LoginActivity activity = new LoginActivity();

        testLogin(activity);
    }


    public static void testLogin(LoginActivity activity)
    {
        //signIn
        mAuth = FirebaseAuth.getInstance();
        String email = "test@hotmail.com";
        String password = "password";
        activity.signIn(mAuth, email, password);

        //onStart
        activity.onStart();
    }
}

