package com.example.kk.pitch.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kk.pitch.Model.GroupModel;
import com.example.kk.pitch.Model.GroupObject;
import com.example.kk.pitch.Model.UserInfo;
import com.example.kk.pitch.Model.UserModel;
import com.example.kk.pitch.R;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.ArrayList;

public class NewGroupActivity extends Activity {

    private ArrayList<GroupObject> groupObjects;
    private EditText groupNameET;
    private EditText addUserEt;
    private String groupName;
    private String newUser;
    private Button addUser;
    private Button confirmGroup;
    private GroupObject groupObject;
    private GroupModel groupModel;
    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        groupModel = new GroupModel();
        userModel = new UserModel();
        groupNameET = findViewById(R.id.group_name_et);
        addUserEt = findViewById(R.id.add_member_et);
        addUser = findViewById(R.id.add_member_button);
        confirmGroup = findViewById(R.id.confirm_group_button);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newUser = addUserEt.getText().toString();
            }
        });

        confirmGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<GroupObject> tempGO = new ArrayList<>();
                tempGO = UserInfo.getInstance().getGroups();

                groupName = groupNameET.getText().toString();

                groupObject = new GroupObject((groupName));
                groupObject.setUniqueId(GroupObject.RandomKey(10));

                tempGO.add(groupObject);
                UserInfo.getInstance().setGroups(tempGO);

                groupModel.addGroup(groupName, groupObject.getUniqueId());
                groupModel.addMembers(newUser, groupObject.getUniqueId());
                groupModel.addMembers(UserInfo.getInstance().getUsername(), groupObject.getUniqueId());
                userModel.addGroupToUser(groupObject.getUniqueId(), UserInfo.getInstance().getUsername());
                userModel.addGroupToUser(groupObject.getUniqueId(), newUser);

                Intent confirm_group_intent = new Intent(NewGroupActivity.this, MainActivity.class);
                startActivity(confirm_group_intent);
            }
        });



    }

}
