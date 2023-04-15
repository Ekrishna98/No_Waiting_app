package com.project.no_waiting.BC_Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.project.no_waiting.Home_Page;
import com.project.no_waiting.R;


public class BC_Login extends AppCompatActivity {
    Button Btn_BCLogin, BC_Submit;
    Spinner Select_BCNumber;
    CardView LoginView, BC_NumberView;
    String[] items = {"0","1", "2", "3", "4"};
    ArrayAdapter<String> adapterItems;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bc_login);
             inits();

        adapterItems = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,items);
        adapterItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Select_BCNumber.setAdapter(adapterItems);

        Btn_BCLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginView.setVisibility(View.GONE);
                BC_NumberView.setVisibility(View.VISIBLE);
            }
        });

        BC_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BC_Login.this,BC_DashBoard_Screen.class));
            }
        });

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void inits() {
        // Login Button
        Btn_BCLogin = findViewById(R.id.BC_Login);
        BC_Submit = findViewById(R.id.BC_Number);
        // CardView
        LoginView = findViewById(R.id.Login_View);
        BC_NumberView = findViewById(R.id.BC_NumberView);
        // Spinner
        Select_BCNumber = findViewById(R.id.Select_BCNumber);

        setTitle("BC Login");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BC_Login.this, Home_Page.class));
        finish();
    }
}