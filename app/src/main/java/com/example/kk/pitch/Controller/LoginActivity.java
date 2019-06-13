package com.example.kk.pitch.Controller;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kk.pitch.Model.LoginModel;
import com.example.kk.pitch.Model.UserModel;
import com.example.kk.pitch.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by KK on 5/10/2019.
 */

public class LoginActivity extends Activity {
    private FirebaseAuth mAuth;
    private Intent login_intent;
    private EditText email_et;
    private EditText password_et;
    private String email;
    private String password;
    private LoginModel model;
    private UserModel userModel;

    public final static String USERMODEL = "usermodel";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Attempt to Launch activity outside our app
        Button voatButton = (Button)findViewById(R.id.FAQButton);
        voatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String faqwebpage = "https://pastebin.com/embed_js/GgK8Pnfs";
                Uri webaddress = Uri.parse(faqwebpage);

                Intent gotoVoat = new Intent(Intent.ACTION_VIEW, webaddress);
                if (gotoVoat.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoVoat);
                }
            }
        });


        mAuth = FirebaseAuth.getInstance();                                                         //Initialize the user
        model = new LoginModel(this);
        login_intent = new Intent(LoginActivity.this, MainActivity.class);            //Initialize intent for MainActivity
        Button login = findViewById(R.id.login_button);                                             //Initialize Login Button
        Button register = findViewById(R.id.Continue_Register);                                      //Initialize the Register Button

        login.setOnClickListener(new View.OnClickListener() {                                       //Response to click on Login Button
            @Override
            public void onClick(View view) {
                email_et = findViewById(R.id.Username);
                email = email_et.getText().toString();
                password_et = findViewById(R.id.password);
                password = password_et.getText().toString();

                signIn(mAuth, email, password);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register_intent = new Intent(LoginActivity.this,               //Initialize intent for RegisterActivity
                        RegisterActivity.class);
                startActivity(register_intent);
            }
        });

    }

    public void signIn(FirebaseAuth mAuth, String email, String password){
        if(email == null || email.equals("")){
            inputError("email");
        }
        else if(password == null || password.equals("")){
            inputError("password");
        }
        else{
            model.signIn(mAuth, email, password);
        }
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
