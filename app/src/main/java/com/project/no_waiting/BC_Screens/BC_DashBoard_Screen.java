package com.project.no_waiting.BC_Screens;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.project.no_waiting.R;

public class BC_DashBoard_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bc_dash_board_screen);
        init();
    }

    private void init() {
        setTitle("BC DashBoard");
    }
}