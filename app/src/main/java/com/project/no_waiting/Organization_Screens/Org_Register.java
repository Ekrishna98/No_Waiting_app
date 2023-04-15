package com.project.no_waiting.Organization_Screens;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.project.no_waiting.R;

public class Org_Register extends AppCompatActivity {
    TextView Already_Login;
    String[] items = {"Select Domain/Sector","Hospital", "Education", "Departmental Store"};
    Spinner Select_Domain;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organization_register);

        init();

        adapterItems = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,items);
        adapterItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Select_Domain.setAdapter(adapterItems);

        Already_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Org_Register.this, Org_Login.class));
                finish();
            }
        });
    }

    private void init() {
        Already_Login = findViewById(R.id.Org_Goto_Login);
        Select_Domain = findViewById(R.id.Select_Domain);

       setTitle("Organization Registration");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Org_Register.this,Org_Login.class));
    }
}