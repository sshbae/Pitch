package com.example.kk.pitch.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kk.pitch.R;

public class RegisterInfoActivity extends Activity {

    private Button reg_button;
    private EditText name_et;
    private EditText username_et;
    private String name;
    private String username;
    private UserController userController;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_2);
        Log.e("i", "am here");
        userController = new UserController(this);

        name_et = findViewById(R.id.name_et);

        username_et = findViewById(R.id.username_et);


        reg_button = findViewById(R.id.reg_button);
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = name_et.getText().toString();
                username = username_et.getText().toString();
                userController.addInfo(username, name);
                Intent reg_intent = new Intent(RegisterInfoActivity.this, MainActivity.class);
                startActivity(reg_intent);
            }
        });


    }

    public void inputError(String s){
        if(s.equals("email")) {
            Toast.makeText(RegisterInfoActivity.this, "Invalid email.",
                    Toast.LENGTH_SHORT).show();
        }
        if(s.equals("name")) {
            Toast.makeText(RegisterInfoActivity.this, "Invalid name.",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
