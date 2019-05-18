package com.example.kk.pitch.Controller;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.kk.pitch.Model.LoginModel;
import com.example.kk.pitch.View.RegisterActivity;
import com.example.kk.pitch.View.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by KK on 5/18/2019.
 */

public class LoginController extends Activity{

    private LoginModel model;
    private LoginActivity context;
    private String email;

    public LoginController(LoginActivity context){
        this.context = context;
        model  = new LoginModel(this);
    }

    public void signIn(final FirebaseAuth mAuth, String email, String password){
        if(email == null || email.equals("")){
            context.inputError("email");
        }
        else if(password == null || password.equals("")){
            context.inputError("password");
        }
        else{
            model.signIn(mAuth, email, password);
        }
    }
    public void response(int i){
        if(i == 1) {
           context.onStart();
        }
        else if(i == 0){
            context.signInError();
        }
    }

}
