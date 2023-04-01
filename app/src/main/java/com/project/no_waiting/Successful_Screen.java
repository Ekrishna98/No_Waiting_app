package com.project.no_waiting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Successful_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_screen);

        Thread t = new Thread(){
            public void run(){
                try{
                    sleep(6000);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(Successful_Screen.this,Home_Page.class));
                    finish();
                }
            }

        };t.start();
    }
}