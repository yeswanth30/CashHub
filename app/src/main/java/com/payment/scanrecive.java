package com.payment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class scanrecive extends AppCompatActivity {
    Spinner bankNameeeSpinner;
    ImageView imageView;
    Button generateQRButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recive_scanner);

        bankNameeeSpinner = findViewById(R.id.bankNameeeSpinner);
        imageView = findViewById(R.id.imageView);
        generateQRButton = findViewById(R.id.button);

        setBankNameOptions();

        bankNameeeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                generateQRCodeForEachBank(); // Update QR code when a different bank is selected
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle when nothing is selected
            }
        });

        generateQRCodeForEachBank(); // Generate initial QR code based on the default bank selection
    }

    private void setBankNameOptions() {
        // Array of bank names
        String[] bankNames = {"AXIS BANK", "SBI BANK", "HDFC BANK", "CITI BANK", "BARCLAYS BANK"};

        // Creating adapter for spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, bankNames);

        // Drop down layout style
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attaching data adapter to spinner
        bankNameeeSpinner.setAdapter(adapter);
    }

    private void generateQRCodeForEachBank() {
        String selectedBankName = bankNameeeSpinner.getSelectedItem().toString();
        Bitmap qrCodeBitmap = generateQRCode(selectedBankName);

        if (qrCodeBitmap != null) {
            imageView.setImageBitmap(qrCodeBitmap);
        }
    }

    private Bitmap generateQRCode(String bankName) {
        final int QRCodeWidth = 350;
        try {
            return textToImageEncode(bankName, QRCodeWidth);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap textToImageEncode(String value, int QRCodeWidth) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    value,
                    BarcodeFormat.QR_CODE,
                    QRCodeWidth,
                    QRCodeWidth,
                    null
            );
        } catch (IllegalArgumentException illegalArgumentException) {
            return null;
        }

        int bitMatrixWidth = bitMatrix.getWidth();
        int bitMatrixHeight = bitMatrix.getHeight();
        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {
                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.black) :
                        getResources().getColor(R.color.white);
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, QRCodeWidth, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}
