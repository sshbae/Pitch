package com.example.kk.pitch.Controller;

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

//import org.junit.Test;

public class TestLoginActivity
{
    public static void main(String[] args)
    {
        System.out.println("testing LoginActivity");
    }

    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    //@Test
    public void test_SignIn()
    {
        LoginActivity activity = new LoginActivity();
        String email = "test@hotmail.com";
        String password = "password";
        activity.signIn(mAuth, email, password);
    }

}
