package com.project.no_waiting;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class Display_TokenNumber extends AppCompatActivity {
    String RunningToken;
    TextView QRCodeDetails, RunningTokenValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_token_number);

        Intent i = getIntent();
        RunningToken = i.getStringExtra("Result");

        QRCodeDetails = findViewById(R.id.QRCodeDetails);
        QRCodeDetails.setText(RunningToken);

        RunningTokenValue = findViewById(R.id.RunningTokenValue);

        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(600); //You can manage the blinking time with this parameter
//        anim.setStartOffset(100);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        RunningTokenValue.startAnimation(anim);

        SuccessScreen();

        setTitle("Display TokenNumber");

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void SuccessScreen() {
        Thread t = new Thread(){
            public void run(){
                try{
                    sleep(20000);
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