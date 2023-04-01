package com.project.no_waiting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class ScanActivity extends AppCompatActivity {


    CodeScanner codeScanner;
    CodeScannerView scannerView1;
    Vibrator vibrate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        vibrate = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        scannerView1 = findViewById(R.id.scannerView);
        codeScanner = new CodeScanner(this,scannerView1);

//        codeScanner.startPreview();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("QRcode Scanner");


//        scannerView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                codeScanner.startPreview();
//            }
//        });


        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                            try {
                                String QRresult = result.getText();
                                vibrate.vibrate(300);
                                Intent intent = new Intent(ScanActivity.this, Display_TokenNumber.class);
                                intent.putExtra("Result", QRresult);
                                startActivity(intent);
                                finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                    }
                });
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        codeScanner.startPreview();
        Log.d("on","onStart Activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
        Log.d("on","onResume Activity");
    }


    @Override
    protected void onPause() {
        codeScanner.releaseResources();
        super.onPause();
//        codeScanner.stopPreview();
        Log.d("on","onPause Activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        codeScanner.stopPreview();
        Log.d("on","onStop Activity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        codeScanner.startPreview();
            Log.d("on","onReStart Activity");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ScanActivity.this,Home_Page.class));
    }
}