package com.example.kk.pitch;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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


import org.junit.Test;

public class LoginActivityTest {
    private FirebaseAuth mAuth;
    private Intent login_intent;
    private EditText email_et;
    private EditText password_et;
    private String email;
    private String password;
    private LoginModel model;
    private UserModel userModel;

    public final static String USERMODEL = "usermodel";

    public static void main(String[] args)
    {
        System.out.println("This compiles???");
    }
    @Test
    private void testLogin(String name)
    {
        assertEquals(name, "garrett");
    }
}



    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();                                                         //Initialize the user
        model = new LoginModel(this);*/
