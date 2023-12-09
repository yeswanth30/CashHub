package com.payment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class bankaccount extends AppCompatActivity {
TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banklink);

        // Find your ImageViews
        ImageView imageView1 = findViewById(R.id.addButton1);
        ImageView imageView2 = findViewById(R.id.addButton2);
        ImageView imageView3 = findViewById(R.id.addButton3);
        ImageView imageView4 = findViewById(R.id.addButton4);
        ImageView imageView5 = findViewById(R.id.addButton5);
        textView2 = findViewById(R.id.textView2);
        // Set OnClickListener for each ImageView
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity for ImageView 1
                Intent intent = new Intent(bankaccount.this, otp.class);
                startActivity(intent);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity for ImageView 2
                Intent intent = new Intent(bankaccount.this, card.class);
                startActivity(intent);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity for ImageView 2
                Intent intent = new Intent(bankaccount.this, otp.class);
                startActivity(intent);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity for ImageView 3
                Intent intent = new Intent(bankaccount.this, otp.class);
                startActivity(intent);
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity for ImageView 4
                Intent intent = new Intent(bankaccount.this, otp.class);
                startActivity(intent);
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Activity for ImageView 5
                Intent intent = new Intent(bankaccount.this, otp.class);
                startActivity(intent);
            }
        });
    }
}
