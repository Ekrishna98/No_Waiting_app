package com.project.nowaiting;

import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class Constants {

    public static boolean checkPermissions(Context context) {
        return ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

    }
}
