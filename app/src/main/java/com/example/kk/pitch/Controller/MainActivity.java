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
    private ImageButton hammen;
    DrawerLayout.DrawerListener menuListener;
    private Intent login_intent;
    private TextView name_tv;
    private FirebaseDatabase  database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private UserController userController = new UserController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);
        final ArrayList<GroupObject> tempGroup = new ArrayList<>();

        myRef.addListenerForSingleValueEvent( new ValueEventListener() {                            //gets user info from database
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                ArrayList<String> tempString = new ArrayList<>();

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String userID = user.getUid();
                DataSnapshot ds_group = snapshot.child("groups");
                DataSnapshot ds_user = snapshot.child("users");
                DataSnapshot ds_user_id = snapshot.child("user_id");
                UserInfo.getInstance().setUsername(ds_user_id.child(userID).getValue().toString());
                UserInfo.getInstance().setName(ds_user.child(UserInfo.getInstance().getUsername()).child("name").getValue().toString());                     //set name
                for(DataSnapshot group : ds_user.child(UserInfo.getInstance().getUsername()).child("groups").getChildren()) {
                    if(group.getValue().equals("true")) {
                        String temp = group.getKey();
                        tempString.add(temp);
                    }
                }
                for(String s : tempString){
                    GroupObject gO = new GroupObject(ds_group.child(s).child("group_name").getValue().toString());
                    gO.setUniqueId(s);
                    for(DataSnapshot s1 : ds_group.child("members").getChildren()){
                        if(s1.getValue().equals("true")) {
                            String temp1 = s1.getKey();
                            gO.addMembers(ds_user.child(temp1).child("name").getValue().toString(), temp1);
                        }
                    }
                    tempGroup.add(gO);
                }

                UserInfo.getInstance().setGroups(tempGroup);

                for(int i = 0; i < UserInfo.getInstance().getGroups().size(); i++) {
                    LinearLayout groupLayout = findViewById(R.id.group_linear_layout);
                    View child = getLayoutInflater().inflate(R.layout.group_entry, null);
                    TextView gName = child.findViewById(R.id.group_name_display);
                    gName.setText(UserInfo.getInstance().getGroups().get(i).getName());
                    final int temp = i;
                    child.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent group_intent = new Intent(MainActivity.this, GroupActivity.class);
                            group_intent.putExtra("index", temp);
                            startActivity(group_intent);
                        }
                    });

                    groupLayout.addView(child);
                }

                name_tv = findViewById(R.id.userName);
                name_tv.setText(UserInfo.getInstance().getName());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ERROR", "ERROR");
            }
        });

        /*ValueEventListener postListener = new ValueEventListener() {
            //refreshes user info upon change
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String userID = user.getUid();

                DataSnapshot ds_user = dataSnapshot.child("users");
                DataSnapshot ds_user_id = dataSnapshot.child("user_id");
                UserInfo.getInstance().setUsername(ds_user_id.child(userID).getValue().toString());
                UserInfo.getInstance().setName(ds_user.child("username").getValue(UserInfo.class).getName());                     //set name
                UserInfo.getInstance().setUsername(ds_user.child("username").getValue(UserInfo.class).getUsername());                   //set email
                name_tv = findViewById(R.id.userName);
                name_tv.setText(UserInfo.getInstance().getName());
                Log.e("on change", "");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Log.w("ERROR", "ERROR");
            }
        };

        myRef.addValueEventListener(postListener);*/



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


        hammen = findViewById(R.id.hammen);
        hammen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(view);

            }
        });

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
