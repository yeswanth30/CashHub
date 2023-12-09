package com.payment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.payment.models.ResponseData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class card extends AppCompatActivity {
    RecyclerView recyclerView;
    private ArrayList<ResponseData.UserData> alldata = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card);
        TextView textView3;
        recyclerView = findViewById(R.id.recyclerView23);
        setRecyclerview();
        textView3 = findViewById(R.id.textView3);
        ImageButton goToBankButton10 = findViewById(R.id.imageButton10);
        ImageButton goToBankButton = findViewById(R.id.imageButton7);
        ImageButton goToBankButton1 = findViewById(R.id.imageButton1);
        ImageButton goToBankButton2 = findViewById(R.id.imageButton2);

        setRecyclerview();

        goToBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(card.this, scanner.class);
                startActivity(intent);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(card.this, bankaccount.class);
                startActivity(intent);
            }
        });

        goToBankButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(card.this, payment_method.class);
                startActivity(intent);
            }
        });
        goToBankButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(card.this, profile.class);
                startActivity(intent);
            }
        });

        goToBankButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(card.this, scanrecive.class);
                startActivity(intent);
            }
        });
    }

    private void setRecyclerview() {
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        int userid = sharedPreferences.getInt("userid", -1);

        if (userid != -1) {

            Call<ResponseData> call = retrofitClient.getInstance().getMyApi().getaccdetail(userid);
            call.enqueue(new Callback<ResponseData>() {
                @Override
                public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                    if (response.isSuccessful()) {
                        ResponseData user = response.body();
                        if (user != null) {
                            alldata = user.getData();

                            CardAdapter adapter = new CardAdapter(alldata);
                            recyclerView.setLayoutManager(new LinearLayoutManager(card.this,LinearLayoutManager.HORIZONTAL,false));
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseData> call, Throwable t) {
                    Toast.makeText(card.this, "An error has occurred" + t, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
