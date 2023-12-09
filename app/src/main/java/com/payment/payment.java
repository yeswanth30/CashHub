//package com.payment;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import dev.shreyaspatil.easyupipayment.EasyUpiPayment;
//import dev.shreyaspatil.easyupipayment.listener.PaymentStatusListener;
//import dev.shreyaspatil.easyupipayment.model.PaymentApp;
//import dev.shreyaspatil.easyupipayment.model.TransactionDetails;
//
//public class payment extends AppCompatActivity implements PaymentStatusListener {
//
//    private ImageView imageView;
//    private TextView statusView;
//    private Button payButton;
//    private EditText fieldPayeeVpa;
//    private EditText fieldPayeeName;
//    private EditText fieldPayeeMerchantCode;
//    private EditText fieldTransactionId;
//    private EditText fieldTransactionRefId;
//    private EditText fieldDescription;
//    private EditText fieldAmount;
//
//    private EasyUpiPayment easyUpiPayment;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.paymentpratice);
//
//        initViews();
//        payButton.setOnClickListener(v -> pay());
//    }
//
//    private void initViews() {
//        imageView = findViewById(R.id.imageView);
//        statusView = findViewById(R.id.textView_status);
//        payButton = findViewById(R.id.button_pay);
//
//        fieldPayeeVpa = findViewById(R.id.field_vpa);
//        fieldPayeeName = findViewById(R.id.field_name);
//        fieldPayeeMerchantCode = findViewById(R.id.field_payee_merchant_code);
//        fieldTransactionId = findViewById(R.id.field_transaction_id);
//        fieldTransactionRefId = findViewById(R.id.field_transaction_ref_id);
//        fieldDescription = findViewById(R.id.field_description);
//        fieldAmount = findViewById(R.id.field_amount);
//
//        String transactionId = "TID" + System.currentTimeMillis();
//        fieldTransactionId.setText(transactionId);
//        fieldTransactionRefId.setText(transactionId);
//    }
//
//    @SuppressLint("NonConstantResourceId")
//    private void pay() {
//        String payeeVpa = fieldPayeeVpa.getText().toString();
//        String payeeName = fieldPayeeName.getText().toString();
//        String payeeMerchantCode = fieldPayeeMerchantCode.getText().toString();
//        String transactionId = fieldTransactionId.getText().toString();
//        String transactionRefId = fieldTransactionRefId.getText().toString();
//        String description = fieldDescription.getText().toString();
//        String amount = fieldAmount.getText().toString();
//
//        PaymentApp paymentApp = PaymentApp.PHONE_PE;
//
//        EasyUpiPayment.Builder builder = new EasyUpiPayment.Builder(this)
//                .with(paymentApp)
//                .setPayeeVpa(payeeVpa)
//                .setPayeeName(payeeName)
//                .setTransactionId(transactionId)
//                .setTransactionRefId(transactionRefId)
//                .setPayeeMerchantCode(payeeMerchantCode)
//                .setDescription(description)
//                .setAmount(amount);
//
//        try {
//            easyUpiPayment = builder.build();
//            easyUpiPayment.setPaymentStatusListener(this);
//            easyUpiPayment.startPayment();
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            toast("Error: " + exception.getMessage());
//        }
//    }
//
//    @Override
//    public void onTransactionCompleted(TransactionDetails transactionDetails) {
//        Log.d("TransactionDetails", transactionDetails.toString());
//        statusView.setText(transactionDetails.toString());
//
//        switch (transactionDetails.getTransactionStatus()) {
//            case SUCCESS:
//                onTransactionSuccess();
//                break;
//            case FAILURE:
//                onTransactionFailed();
//                break;
//            case SUBMITTED:
//                onTransactionSubmitted();
//                break;
//        }
//    }
//
//    @Override
//    public void onTransactionCancelled() {
//        toast("Cancelled by user");
//    }
//
//    private void onTransactionSuccess() {
//        toast("Success");
//    }
//
//    private void onTransactionSubmitted() {
//        toast("Pending | Submitted");
//    }
//
//    private void onTransactionFailed() {
//        toast("Failed");
//    }
//
//    private void toast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//    }
//}
