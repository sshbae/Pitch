package com.example.kk.pitch.Model;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.kk.pitch.View.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by KK on 5/18/2019.
 */

public class RegisterModel extends Activity{

    private RegisterActivity context;

    public RegisterModel(RegisterActivity context){
        this.context = context;
    }

    public void createAccount(FirebaseAuth mAuth, String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("CREATE", "createUserWithEmail:success");
                            context.startIntent();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("CREATE", "createUserWithEmail:failure", task.getException());
                            context.regError();
                        }

                        // ...
                    }
                });
    }
}
