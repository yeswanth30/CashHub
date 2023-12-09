package com.payment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.payment.Dbhelper.dbhelper;
import com.payment.models.signmodel;

import java.util.List;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment {
    EditText editTextNewUsername;
    EditText editTextNewPassword,editTextNewNumber,editTextNewEmail;
    Button buttonSignUp;
    dbhelper dbhelper;

    public SignUpFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_singup, container, false);

        // Find your sign-up button
        Button signUpButton = view.findViewById(R.id.buttonSignUp);
        editTextNewUsername = view.findViewById(R.id.editTextNewUsername);
        editTextNewNumber = view.findViewById(R.id.editTextNewNumber);
        editTextNewEmail=view.findViewById(R.id.editTextNewEmail);
        editTextNewPassword=view.findViewById(R.id.editTextNewPassword);
        dbhelper = new dbhelper(requireContext());

        // Set OnClickListener for the sign-up button
         signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create request data
                signmodel requestData = new signmodel();
                requestData.setName(editTextNewUsername.getText().toString());
                requestData.setEmail(editTextNewEmail.getText().toString());
                requestData.setNumber(editTextNewNumber.getText().toString());
                requestData.setPassword(editTextNewPassword.getText().toString());


                Call<signmodel> call = retrofitClient.getInstance().getMyApi().signup(requestData);
                call.enqueue(new Callback<signmodel>() {
                    @Override
                    public void onResponse(Call<signmodel> call, Response<signmodel> response) {
                        if (response.isSuccessful()) {
                            // Handle successful response
                            signmodel data = response.body();
                            Log.e("signup response",data.toString());
                        } else {
                            // Handle other response codes

                        }
                    }

                    @Override
                    public void onFailure(Call<signmodel> call, Throwable t) {
                        // Handle failure
                    }
                });


                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }
    private void saveUsernameToSharedPreferences(String username) {
        // Get SharedPreferences instance
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);

        // Save the username using SharedPreferences Editor
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.apply();
    }
}
