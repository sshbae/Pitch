package com.example.kk.pitch;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TabHost;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Activity {

    private Button sign_out;
    private Intent login_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        TabHost tabHost = findViewById(R.id.tabHost);
        tabHost.setup();

        //tab1
        TabHost.TabSpec spec = tabHost.newTabSpec("Tab one");
        spec.setContent(R.id.Profile);
        spec.setIndicator("PROFILE");
        tabHost.addTab(spec);

        //tab2
        spec = tabHost.newTabSpec("Tab two");
        spec.setContent(R.id.Groups);
        spec.setIndicator("GROUPS");
        tabHost.addTab(spec);

        sign_out = findViewById(R.id.button);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login_intent = new Intent(MainActivity.this, LoginActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(login_intent);
            }
        });
    }
}
