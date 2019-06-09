package com.example.kk.pitch.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.kk.pitch.Model.GroupObject;
import com.example.kk.pitch.Model.UserInfo;
import com.example.kk.pitch.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

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
    private ArrayList<GroupObject> groupObjects = new ArrayList<>();

    public final static String GROUPOBJECTS = "groupobjects";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            groupObjects = extras.getParcelableArrayList("name");
            // and get whatever type user account id is
        }else{
            groupObjects = new ArrayList<GroupObject>();
            if(groupObjects == null){
                Log.e("i", "am null2");
            }
        }
        for(int i = 0; i < groupObjects.size(); i++) {
            LinearLayout groupLayout = findViewById(R.id.group_linear_layout);
            View child = getLayoutInflater().inflate(R.layout.activity_group_display, null);
            TextView gName = child.findViewById(R.id.group_name_display);
            gName.setText(groupObjects.get(i).getName());
            groupLayout.addView(child);
        }


        uInfo = new UserInfo();


        myRef.addListenerForSingleValueEvent( new ValueEventListener() {                            //gets user info from database
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String userID = user.getUid();

                DataSnapshot ds = snapshot.child("users");
                    uInfo.setName(ds.child(userID).getValue(UserInfo.class).getName());                     //set name
                    //uInfo.setEmail(ds.child(userID).getValue(UserInfo.class).getEmail());                   //set email
                    name_tv = findViewById(R.id.userName);
                    name_tv.setText(uInfo.getName());

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

                DataSnapshot ds = dataSnapshot.child("users");
                uInfo.setName(ds.child(userID).getValue(UserInfo.class).getName());                     //set name
                //uInfo.setEmail(ds.child(userID).getValue(UserInfo.class).getEmail());                   //set email
                name_tv = findViewById(R.id.userName);
                name_tv.setText(uInfo.getName());

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


        FloatingActionButton createGroup = findViewById(R.id.create_group_button);
        createGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createGroupIntent = new Intent(MainActivity.this, NewGroupActivity.class);
                createGroupIntent.putExtra(GROUPOBJECTS, groupObjects);
                startActivity(createGroupIntent);
            }
        });


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
