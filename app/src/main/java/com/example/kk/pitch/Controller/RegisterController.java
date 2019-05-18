package com.example.kk.pitch.Controller;

import android.opengl.GLSurfaceView;
import android.view.View;
import android.widget.Button;

import com.example.kk.pitch.Model.RegisterModel;
import com.example.kk.pitch.View.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by KK on 5/18/2019.
 */

public class RegisterController {

    private RegisterActivity context;
    private RegisterModel model;

    public RegisterController(RegisterActivity context){
        this.context = context;
        model = new RegisterModel(this);
    }

    public void register(Button registerButton, final String email, final String password, final String confirm, final FirebaseAuth mAuth){
        if(email == null || email.equals("")){
            context.inputError("email");
        }
        else if(password == null || password.equals("")){
            context.inputError("password");
        }
        else if(confirm == null || confirm.equals("")){
            context.inputError("confirm");
        }
        else if(!password.equals(confirm)){
            context.inputError("match");
        }
        else {
            model.createAccount(mAuth, email, password);
        }
    }

    public void response(int i){
        if(i == 0) {
            context.startIntent();
        }
        else if(i == 1){
            context.regError();
        }
    }

}
