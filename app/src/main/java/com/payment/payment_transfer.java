package com.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.payment.R;

public class payment_transfer extends AppCompatActivity {

    private EditText editTextNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_transfer);

        ImageButton goToBankButton = findViewById(R.id.imageButton6);
        editTextNumber = findViewById(R.id.editTextNumber); // Replace with your EditText ID

        // Set OnClickListener for the button
        goToBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextNumber.getText().toString().isEmpty()) {
                    // Show a message indicating details are required
                    Toast.makeText(payment_transfer.this, "Please Enter the Amount", Toast.LENGTH_SHORT).show();
                } else {
                    // Proceed to the BankAccountActivity
                    Intent intent = new Intent(payment_transfer.this, payment_done.class);
                    startActivity(intent);
                }
            }
        });
    }
}
