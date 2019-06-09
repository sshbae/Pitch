package com.example.kk.pitch.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kk.pitch.Model.GroupObject;
import com.example.kk.pitch.R;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.ArrayList;

public class NewGroupActivity extends Activity {

    private ArrayList<GroupObject> groupObjects;
    private EditText groupNameET;
    private String groupName;
    private Button confirmGroup;
    private GroupObject groupObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.e("i", "already exists");
            groupObjects = extras.getParcelableArrayList(MainActivity.GROUPOBJECTS);
            // and get whatever type user account id is
        }else{
            Log.e("i", "create new");
            groupObjects = new ArrayList<GroupObject>();
        }

        groupNameET = findViewById(R.id.group_name_et);




        confirmGroup = findViewById(R.id.confirm_group_button);
        confirmGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                groupName = groupNameET.getText().toString();
                groupObject = new GroupObject((groupName));
                groupObjects.add(groupObject);

                Intent confirm_group_intent = new Intent(NewGroupActivity.this, MainActivity.class);
                confirm_group_intent.putExtra(MainActivity.GROUPOBJECTS, groupObjects);
                startActivity(confirm_group_intent);
            }
        });



    }

}
