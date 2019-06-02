package com.example.kk.pitch.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.kk.pitch.Model.UserInfo;
import com.example.kk.pitch.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends Activity {

    private Button sign_out;
    private ImageButton menu;
    DrawerLayout.DrawerListener menuListener;
    private Intent login_intent;
    private TextView name_tv;
    private UserInfo uInfo;
    private FirebaseDatabase  database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private UserController userController = new UserController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_profile);

        uInfo = new UserInfo();

        myRef.addListenerForSingleValueEvent( new ValueEventListener() {                            //gets user info from database
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String userID = user.getUid();
                for(DataSnapshot ds : snapshot.getChildren()) {
                    uInfo.setName(ds.child(userID).getValue(UserInfo.class).getName());                     //set name
                    //uInfo.setEmail(ds.child(userID).getValue(UserInfo.class).getEmail());                   //set email
                    name_tv = findViewById(R.id.userName);
                    name_tv.setText(uInfo.getName());
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ERROR", "ERROR");
            }
        });


        ValueEventListener postListener = new ValueEventListener() {                                //refreshes user info upon change
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String userID = user.getUid();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    uInfo.setName(ds.child(userID).getValue(UserInfo.class).getName());                     //set name
                    //uInfo.setEmail(ds.child(userID).getValue(UserInfo.class).getEmail());                   //set email
                    name_tv = findViewById(R.id.userName);
                    name_tv.setText(uInfo.getName());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        };

        myRef.addValueEventListener(postListener);

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



        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //drawer.addDrawerListener(menuListener);

        //menu = findViewById(R.id.hamburgerMenu);
        //menu.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                /*pull out the menu*/
                //drawer.openDrawer(view);
            //}
        //});


        sign_out = findViewById(R.id.sign_out_button);
        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login_intent = new Intent(MainActivity.this, LoginActivity.class);
                FirebaseAuth.getInstance().signOut();
                startActivity(login_intent);
            }
        });
    }

    public void paymentRequest(){};
}
