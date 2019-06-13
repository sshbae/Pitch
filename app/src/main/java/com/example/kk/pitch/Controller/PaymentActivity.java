package com.example.kk.pitch.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kk.pitch.R;

public class PaymentActivity extends Activity {

    private String purchaseName;
    private String amount;
    private EditText purchaseNameET;
    private EditText amountTV;
    private Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        purchaseNameET = findViewById(R.id.purchase_name_et);
        purchaseName = purchaseNameET.getText().toString();
        amountTV = findViewById(R.id.amount_et);
        amount = amountTV.getText().toString();
        create = findViewById(R.id.create_payment_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
