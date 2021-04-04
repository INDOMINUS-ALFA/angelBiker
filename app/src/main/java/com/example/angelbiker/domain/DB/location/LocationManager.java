package com.example.angelbiker.domain.DB.location;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.angelbiker.ui.dialogs.GpsAlertDialogFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import static androidx.core.content.ContextCompat.getSystemService;


public class LocationManager {

    public static final int REQUEST_CODE_LOCATION = 0;

    private static FusedLocationProviderClient fusedLocationProviderClient;

    static Location currentCachedLocation = null;

    enum LocationMode {
        NORMAL, CACHE
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void getLocationCurrentCache(Activity activity) {
        if (currentCachedLocation == null) {
            getLocation(activity, LocationMode.CACHE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void getLocationCurrent(Activity activity) {
        getLocation(activity, LocationMode.NORMAL);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void getLocation(Activity activity, final LocationMode locationMode) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
        if (ContextCompat.checkSelfPermission(
                activity, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (isGpsEnabled(activity)) {
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
                    Location location = task.getResult();
                    if (location != null) {
                        if (locationMode == LocationMode.CACHE) {
                            LocationManager.currentCachedLocation = location;
                        }
                        ((LocationManagerHandler) activity).onLocationChanged(location);
                    }
                });
            } else {
                showGPSDisabledAlertDialog((AppCompatActivity) activity);
            }

        } else {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            activity.requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE_LOCATION);
        }
    }


    public static void onRequestedLocationPermissionsResult(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (isGpsEnabled(activity)) {
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
                    Location location = task.getResult();
                    if (location != null) {
                        ((LocationManagerHandler) activity).onLocationChanged(location);
                    }
                });
            } else {
                showGPSDisabledAlertDialog((AppCompatActivity) activity);
            }
        } else {
            // Explain to the user that the feature is unavailable because
            // the features requires a permission that the user has denied.
            // At the same time, respect the user's decision. Don't link to
            // system settings in an effort to convince the user to change
            // their decision.
            ((LocationManagerHandler) activity).onLocationPermissionDenied();
        }

    }

    private static boolean isGpsEnabled(Context context) {
        android.location.LocationManager locationManager = getSystemService(context, android.location.LocationManager.class);
        return locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
    }

    private static void showGPSDisabledAlertDialog(AppCompatActivity activity) {
        GpsAlertDialogFragment.newInstance().show(activity.getSupportFragmentManager(), "");
    }

    public interface LocationManagerHandler {
        void onLocationChanged(Location location);
        void onLocationPermissionDenied();
    }

}
