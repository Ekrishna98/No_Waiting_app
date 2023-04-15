package com.project.no_waiting.Organization_Screens;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.project.no_waiting.Home_Page;
import com.project.no_waiting.R;

public class Org_Login extends AppCompatActivity implements View.OnClickListener{
    TextView signUp_button, forgot_button;
    Button Org_Login, Back_button;
    LinearLayout loginView, forgotView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organization_login);

        init();
    }

    private void init() {
        setTitle("Organization Login");

        signUp_button = findViewById(R.id.Org_SignUp);
        signUp_button.setOnClickListener(this);

        forgot_button = findViewById(R.id.Org_forgotPwd);
        forgot_button.setOnClickListener(this);

        Org_Login = findViewById(R.id.Org_BtnLogin);
        Org_Login.setOnClickListener(this);

        Back_button = findViewById(R.id.Org_BtnBack);
        Back_button.setOnClickListener(this);

        //LinearLayout Views
        loginView = findViewById(R.id.Org_loginView);
        forgotView = findViewById(R.id.Org_forgotView);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Org_Login.this, Home_Page.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Org_SignUp:
                startActivity(new Intent(Org_Login.this, Org_Register.class));
                finish();
                break;

            case R.id.Org_BtnLogin:
                startActivity(new Intent(Org_Login.this, Org_DashBoard.class));
                break;

            case R.id.Org_forgotPwd:
                DisplayForgot();
                break;

            case R.id.Org_BtnBack:
                BackLogin();
        }

    }

    private void BackLogin() {
        loginView.setVisibility(View.VISIBLE);
        forgotView.setVisibility(View.GONE);
    }

    private void DisplayForgot() {
        loginView.setVisibility(View.GONE);
        forgotView.setVisibility(View.VISIBLE);
    }
}