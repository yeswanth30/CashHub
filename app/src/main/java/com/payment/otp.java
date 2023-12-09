package com.payment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.payment.Dbhelper.dbhelper;
import com.payment.models.bankk;
import com.payment.models.signmodel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class otp extends AppCompatActivity {
    EditText editTextAccountNumber, balance, ifc1;
    Spinner bankNameSpinner, accountTypeSpinner;
    TextView textView2;
    dbhelper dphelper;
    String[] bankNames = {"AXIS BANK", "SBI BANK ", "HDFC BANK", "CITI BANK", "BARCLAYS BANK"};
    String[] bankext = {"@axl", "@oksbi", "@ybl", "@citi", "@barclay"};
    String UPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);

        editTextAccountNumber = findViewById(R.id.editTextAccountNumber);
        accountTypeSpinner = findViewById(R.id.accountTypeSpinner);
        bankNameSpinner = findViewById(R.id.bankNameSpinner);
        balance = findViewById(R.id.balance);
        ifc1 = findViewById(R.id.ifc1);

        // Find your button
        Button goToBankButton = findViewById(R.id.button);
        dphelper = new dbhelper(otp.this);

        // Set bank name options in the spinner
        setBankNameOptions();
        setAccountTypeOptions();

        // Set OnClickListener for the button
        goToBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
                int userid = sharedPreferences.getInt("userid", 0);
                String username = sharedPreferences.getString("username", "");
                Log.e("userid", String.valueOf(userid));

                String accountNumber = editTextAccountNumber.getText().toString();
                String balanceAmount = balance.getText().toString();
                String ifscCode = ifc1.getText().toString();
                String selectedBankName = bankNameSpinner.getSelectedItem().toString();

                if (accountNumber.isEmpty() || balanceAmount.isEmpty() || ifscCode.isEmpty() || selectedBankName.isEmpty()) {
                    Toast.makeText(otp.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    bankk requestData = new bankk();
                    requestData.setAccount_number(accountNumber);
                    requestData.setAccount_type(accountTypeSpinner.getSelectedItem().toString());
                    requestData.setBank_name(selectedBankName);
                    requestData.setBalance(balanceAmount);
                    requestData.setIFSC_Code(ifscCode);

                    for (int i = 0; i < bankNames.length; i++) {
                        if (selectedBankName.equals(bankNames[i])) {
                            UPI = username + bankext[i];
                            break;
                        }
                    }
                    requestData.setUPI(UPI);
                    requestData.setUser_id(userid);

                    Call<bankk> call = retrofitClient.getInstance().getMyApi().banklink(requestData);
                    call.enqueue(new Callback<bankk>() {
                        @Override
                        public void onResponse(Call<bankk> call, Response<bankk> response) {
                            if (response.isSuccessful()) {
                                bankk data = response.body();
                                Log.e("signup response", data.toString());
                                Intent intent = new Intent(otp.this, card.class);
                                startActivity(intent);
                            } else {
                                // Handle other response codes
                            }
                        }

                        @Override
                        public void onFailure(Call<bankk> call, Throwable t) {
                            // Handle failure
                        }
                    });
                }
            }
        });
    }

    private void setAccountTypeOptions() {
        String[] accountTypes = {"Current", "Savings"};
        ArrayAdapter<String> accountTypeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, accountTypes);
        accountTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(accountTypeAdapter);
    }

    private void setBankNameOptions() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, bankNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bankNameSpinner.setAdapter(adapter);
    }
}
