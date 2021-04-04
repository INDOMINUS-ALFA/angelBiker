package com.example.angelbiker.ui.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class MapViewModel extends AndroidViewModel {

    private double latitude, longitude;

    public MapViewModel(@NonNull Application application, double latitude, double longitude) {
        super(application);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
