package com.example.kk.pitch.Controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.kk.pitch.R;

public class FAQActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Attempt to Launch activity outside our app
        Button voatButton = (Button)findViewById(R.id.FAQButton);
        voatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String faqwebpage = "https://docs.google.com/document/d/1z64be2NjPu2uxWXdGDz8oREPz-gEfehlOYMaZD9hapk/edit?usp=sharing";
                Uri webaddress = Uri.parse(faqwebpage);

                Intent gotoVoat = new Intent(Intent.ACTION_VIEW, webaddress);
                if (gotoVoat.resolveActivity(getPackageManager()) != null) {
                    startActivity(gotoVoat);
                }
            }
        });
    }
}
