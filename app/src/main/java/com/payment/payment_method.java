package com.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class payment_method extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_method);

        // Find your button
        ImageButton goToBankButton = findViewById(R.id.imageButton5);

        // Set OnClickListener for the button
        goToBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the BankAccountActivity
                Intent intent = new Intent(payment_method.this, payment_transfer.class);
                startActivity(intent);

            }
        });
    }
}

