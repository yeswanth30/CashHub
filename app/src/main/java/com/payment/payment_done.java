package com.payment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class payment_done extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentdone);

        ImageView imageView = findViewById(R.id.imageButton10);

        // Set the image resource
        imageView.setImageResource(R.drawable.downloaddone); // Replace "your_image" with your image resource

        // Make the text visible
        TextView successMessageTextView = findViewById(R.id.successMessageTextView);
        successMessageTextView.setVisibility(View.VISIBLE);

        // Create a Handler to post a delayed action
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the next activity after a delay of 30 seconds (30000 milliseconds)
                navigateToNextActivity();
            }
        }, 2000); // Set the delay in milliseconds (30 seconds in this case)
    }

    private void navigateToNextActivity() {
        // Start the next activity here
        // Replace 'NextActivity.class' with your intended activity class
        Intent intent = new Intent(payment_done.this, card.class);
        startActivity(intent);
        finish(); // Finish the current activity if needed
    }
}
