package com.example.kk.pitch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by KK on 5/12/2019.
 */

public class RegisterActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        
        Button registerButton = findViewById(R.id.register_button2);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login_intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(login_intent);
            }
        });
    }

}
