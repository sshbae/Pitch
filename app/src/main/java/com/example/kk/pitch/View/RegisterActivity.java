package com.example.kk.pitch.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kk.pitch.Controller.RegisterController;
import com.example.kk.pitch.Controller.UserController;
import com.example.kk.pitch.Model.UserModel;
import com.example.kk.pitch.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by KK on 5/12/2019.
 */

public class RegisterActivity extends Activity {

    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private String confirm;
    private String name;
    private Intent login_intent;
    private EditText email_et;
    private EditText password_et;
    private EditText confirm_et;
    private EditText name_et;
    private RegisterController controller;
    private UserController userController;
    private Button registerButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        controller = new RegisterController(this);
        userController = new UserController(this);
        login_intent = new Intent(RegisterActivity.this, LoginActivity.class);
        registerButton = findViewById(R.id.register_button2);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email_et = findViewById(R.id.email);
                email = email_et.getText().toString();
                password_et = findViewById(R.id.password);
                password = password_et.getText().toString();
                confirm_et = findViewById(R.id.confirm);
                confirm = confirm_et.getText().toString();
                name_et = findViewById(R.id.name);
                name = name_et.getText().toString();

                controller.register(email, password, confirm, mAuth);

            }
        });
    }

    public void startIntent(){
        userController.addInfo(email, name);
        startActivity(login_intent);
    }

    public void regError(){
        Toast.makeText(RegisterActivity.this, "Registration failed.",
                Toast.LENGTH_SHORT).show();
    }

    public void inputError(String s){
        if(s.equals("email")) {
            Toast.makeText(RegisterActivity.this, "Invalid email.",
                    Toast.LENGTH_SHORT).show();
        }
        if(s.equals("password")){
            Toast.makeText(RegisterActivity.this, "Invalid password.",
                    Toast.LENGTH_SHORT).show();
        }
        if(s.equals("confirm")) {
            Toast.makeText(RegisterActivity.this, "Invalid password confirmation.",
                    Toast.LENGTH_SHORT).show();
        }
        if(s.equals("name")) {
            Toast.makeText(RegisterActivity.this, "Invalid name.",
                    Toast.LENGTH_SHORT).show();
        }
        if(s.equals("match")){
            Toast.makeText(RegisterActivity.this, "Passwords no not match.",
                    Toast.LENGTH_SHORT).show();
        }
    }


}
