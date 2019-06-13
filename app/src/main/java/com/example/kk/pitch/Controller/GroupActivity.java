package com.example.kk.pitch.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import com.example.kk.pitch.Model.UserInfo;
import com.example.kk.pitch.R;

public class GroupActivity extends Activity {

    private FloatingActionButton makePayment;
    private TextView groupNameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_page);

        Bundle extras = getIntent().getExtras();
        int index = 0;

        if (extras != null) {
            index = extras.getInt("index");
            // and get whatever type user account id is
        }

        groupNameTv = findViewById(R.id.display_group_name_tv);
        groupNameTv.setText(UserInfo.getInstance().getGroups().get(index).getName());

        makePayment = findViewById(R.id.make_purchase_button);
        makePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paymentIntent = new Intent(GroupActivity.this, PaymentActivity.class);
                startActivity(paymentIntent);
            }
        });


    }
}
