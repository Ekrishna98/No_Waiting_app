package com.project.nowaiting;



import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button scanButton;
    Context cotext;

    int PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanButton = findViewById(R.id.BtnScanner);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(Constants.checkPermissions(getApplicationContext())){

              }else {
                  requestPermissions();
              }
            }
        });
    }

    /** QRCode Scan Method()  **/
    private void ScanQRCode() {
        GmsBarcodeScannerOptions options = new GmsBarcodeScannerOptions.Builder()
                .setBarcodeFormats(
                        Barcode.FORMAT_QR_CODE,
                        Barcode.FORMAT_AZTEC)
                .build();

        GmsBarcodeScanner scanner = GmsBarcodeScanning.getClient(MainActivity.this, options);

        scanner
                .startScan()
                .addOnSuccessListener(
                        barcode -> {
                            String rawValue = barcode.getRawValue();
                            Log.v("", "krishna" + rawValue);
                            Intent intent = new Intent(MainActivity.this, Display_TokenNumber.class);
                            intent.putExtra("Token", rawValue);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "QRCode Values " + rawValue, Toast.LENGTH_SHORT).show();
                            // Task completed successfully
                        })
                .addOnCanceledListener(
                        () -> {
                            Toast.makeText(MainActivity.this, "Your Cancelled Scanner!...", Toast.LENGTH_SHORT).show();
                            // Task canceled
                        })
                 .addOnFailureListener(
                        e -> {
                            Toast.makeText(MainActivity.this, "Failed to QRCode Scanner!...", Toast.LENGTH_SHORT).show();
                            // Task failed with an exception
                        });


    }


    /** request Permissions Method **/
        private boolean requestPermissions() {
            Dexter.withActivity(this)
                    .withPermissions(android.Manifest.permission.CAMERA )
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                            if (multiplePermissionsReport.areAllPermissionsGranted()) {
                                ScanQRCode();
//                                Toast.makeText(MainActivity.this, "All the permissions are granted..", Toast.LENGTH_SHORT).show();
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
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

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
                finish();
                dialog.cancel();
            });
            builder.show();
        }

}


