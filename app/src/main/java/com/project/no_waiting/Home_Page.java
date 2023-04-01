package com.project.no_waiting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.project.no_waiting.BC_Screens.BC_Login;
import com.project.no_waiting.Organization_Screens.Org_Login;
import com.project.no_waiting.Organization_Screens.Org_Register;

import java.util.List;

public class Home_Page extends AppCompatActivity {

    String QRresult;
    Button scanButton, TestButton;
    private ActionBarDrawerToggle toggle;
    private NavigationView menu_Button;
    private DrawerLayout drawer;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        inits();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Constants.checkPermissions(getApplicationContext())) {
                }
                else {
                    requestPermissions();
                }
            }

        });

        TestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home_Page.this, Org_Register.class));
                finish();
            }
        });

        Menu_Button_SelectOptions();
    }

    private void inits() {

        scanButton = findViewById(R.id.BtnScanner);
        TestButton = findViewById(R.id.BtnTest);
        // Navigation Drawer Stating...
        menu_Button = findViewById(R.id.Menu_Btn);
        drawer = findViewById(R.id.drawerLog);
        menu_Button.setVisibility(View.VISIBLE);

        toggle = new ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void Menu_Button_SelectOptions() {

        menu_Button.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.BCExecutives:
                        startActivity(new Intent(Home_Page.this,BC_Login.class));
//                        Toast.makeText(Home_Page.this, "BC-Executives open", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.Org_Login:
                        startActivity(new Intent(Home_Page.this, Org_Login.class));
//                        Toast.makeText(Home_Page.this, "Organization_Login open", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                    case R.id.logout:
                        Toast.makeText(Home_Page.this, "logout", Toast.LENGTH_SHORT).show();
                        drawer.closeDrawer(GravityCompat.START);
                        finish();
                        break;
                }
                return true;
            }
        });

    }


    private void requestPermission() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
//                            Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();

                        } else if (report.isAnyPermissionPermanentlyDenied()) {
                            Toast.makeText(Home_Page.this, "Camera Permission Required!...", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }


    /** request Permissions Method **/
    private boolean requestPermissions(){
        Dexter.withActivity(this)
                .withPermissions(android.Manifest.permission.CAMERA )
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        if (multiplePermissionsReport.areAllPermissionsGranted()) {

                            Intent intent = new Intent(Home_Page.this, ScanActivity.class);
                            startActivity(intent);
                            finish();
//
                        }
                        if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).withErrorListener(error -> {
                    Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                })
                .onSameThread().check();
        return true;
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Home_Page.this);

        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", (dialog, which) -> {
            dialog.cancel();
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivityForResult(intent, 101);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
//            finish();
            dialog.cancel();
            return;
        });
        builder.show();
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }
}