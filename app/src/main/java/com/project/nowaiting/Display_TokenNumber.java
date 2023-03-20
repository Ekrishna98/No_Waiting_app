package com.project.nowaiting;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Display_TokenNumber extends AppCompatActivity {

    String RunningToken;

    TextView QRCodeDetails;
    Button scanButton1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_token_number);

        Intent i = getIntent();

        RunningToken = i.getStringExtra("Token");
        QRCodeDetails = findViewById(R.id.QRCodeDetails);
        QRCodeDetails.setText(RunningToken);
    }
}