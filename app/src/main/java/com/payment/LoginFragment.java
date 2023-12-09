package com.payment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.payment.Dbhelper.dbhelper;
import com.payment.models.login;
import com.payment.models.loginn;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {
    EditText editTextNewUsername, editTextNewPassword;
    dbhelper dbHelper;
    Integer id;
    private Api api;

    public LoginFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button signInButton = view.findViewById(R.id.buttonSignUp);
        editTextNewUsername = view.findViewById(R.id.editTextNewUsername);
        editTextNewPassword = view.findViewById(R.id.editTextNewPassword);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringEmail = editTextNewUsername.getText().toString();
                String stringPassword = editTextNewPassword.getText().toString();

                if (stringEmail.isEmpty() || stringPassword.isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter Values", Toast.LENGTH_LONG).show();
                } else {
                    loginn requestData = new loginn();
                    requestData.setEmail(stringEmail);
                    requestData.setPassword(stringPassword);

                    Call<login> call = retrofitClient.getInstance().getMyApi().login(requestData);
                    call.enqueue(new Callback<login>() {
                        @Override
                        public void onResponse(Call<login> call, Response<login> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                login signinResponse = response.body();

                                List<login.DataItem1> dataList = signinResponse.getData();
                                for (login.DataItem1 dataItem : dataList) {
                                    id = dataItem.getId();
                                }

                                if (dataList != null && !dataList.isEmpty()) {
                                    login.DataItem1 dataItem = dataList.get(0);
                                    id = dataItem.getId();

                                    SharedPreferences sharedpreferences = getContext().getSharedPreferences("my_preferences", MODE_PRIVATE);
                                    SharedPreferences.Editor myedit = sharedpreferences.edit();
                                    myedit.putInt("userid", id);
                                    Log.e("UserID", "User ID: " + id);
                                    myedit.apply();

                                    Intent intent = new Intent(getActivity(), bankaccount.class);
                                    startActivity(intent);
                                    requireActivity().finish();
                                } else {
                                    // Handle the case when dataList is empty or null
                                }

//                                login signinResponse = response.body();

                                if (signinResponse.getMessage().equals("Email not found")) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getContext(), "Email not found", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else if (signinResponse.getMessage().equals("Incorrect password")) {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                            builder.setTitle("Login Failed")
                                                    .setMessage("Invalid credentials. Please check your email and password.")
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            // You can optionally add functionality here if the user clicks "OK"
                                                        }
                                                    })
                                                    .show();
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<login> call, Throwable t) {
                            // Handle failure
                        }
                    });
                }
            }
        });

        return view;
    }
}
