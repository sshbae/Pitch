package com.example.kk.pitch.Controller;

import com.google.firebase.auth.FirebaseAuth;

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
