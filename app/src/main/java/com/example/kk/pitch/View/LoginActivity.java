package com.example.kk.pitch.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kk.pitch.Controller.LoginController;
import com.example.kk.pitch.MainActivity;
import com.example.kk.pitch.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by KK on 5/10/2019.
 */

public class LoginActivity extends Activity {
    private FirebaseAuth mAuth;
    private Intent login_intent;
    private LoginController controller;
    private EditText email_et;
    private EditText password_et;
    private String email;
    private String password;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();                                                         //Initialize the user
        controller = new LoginController(this);
        login_intent = new Intent(LoginActivity.this, MainActivity.class);            //Initialize intent for MainActivity
        Button login = findViewById(R.id.login_button);                                             //Initialize Login Button
        Button register = findViewById(R.id.register_button2);                                      //Initialize the Register Button

        login.setOnClickListener(new View.OnClickListener() {                                       //Response to click on Login Button
            @Override
            public void onClick(View view) {
                email_et = findViewById(R.id.email);
                email = email_et.getText().toString();
                password_et = findViewById(R.id.password);
                password = password_et.getText().toString();

                controller.signIn(mAuth, email, password);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register_intent = new Intent(LoginActivity.this, RegisterActivity.class);               //Initialize intent for RegisterActivity
                startActivity(register_intent);
            }
        });

    }

    public void onStart(){
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            startActivity(login_intent);
        }
    }

    public void signInError(){
        Toast.makeText(LoginActivity.this, "Authentication failed.",
            Toast.LENGTH_SHORT).show();
    }

    public void inputError(String s){
        if(s.equals("email")) {
            Toast.makeText(LoginActivity.this, "Invalid email.",
                    Toast.LENGTH_SHORT).show();
        }
        if(s.equals("password")){
            Toast.makeText(LoginActivity.this, "Invalid password.",
                    Toast.LENGTH_SHORT).show();
        }
    }





}
