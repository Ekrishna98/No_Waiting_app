package com.project.no_waiting_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Display_TokenNumber extends AppCompatActivity {

    String RunningToken;

    TextView QRCodeDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_token_number);

        Intent i = getIntent();

        RunningToken = i.getStringExtra("Result");
        QRCodeDetails = findViewById(R.id.QRCodeDetails);
        QRCodeDetails.setText(RunningToken);

        Thread t = new Thread(){
            public void run(){
                try{
                    sleep(6000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(Display_TokenNumber.this,Successful_Screen.class));
                    finish();
                }
            }

        };t.start();

    }
}