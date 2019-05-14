package com.example.kk.pitch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

/**
 * Created by KK on 5/12/2019.
 */

public class RegisterActivity extends Activity {

    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private Intent login_intent;
    private EditText email_et;
    private EditText password_et;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        login_intent = new Intent(RegisterActivity.this, LoginActivity.class);

        EditText email_et = findViewById(R.id.email);
        email = email_et.getText().toString();

        EditText password_et = findViewById(R.id.password);
        password = password_et.getText().toString();

        Button registerButton = findViewById(R.id.register_button2);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();

            }
        });
    }

    public void createAccount(){
        email_et = findViewById(R.id.email);
        email = email_et.getText().toString();

        password_et = findViewById(R.id.password);
        password = password_et.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("CREATE", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(login_intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("CREATE", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

}
