package com.example.angelbiker.domain.DB.modelos.location;

import android.location.Location;

import androidx.annotation.NonNull;

public class LocationMinimal {
    private String name;
    private String photoPath;
    private double latitude, longitude;

    public LocationMinimal(String name, String photoPath, double latitude, double longitude) {
        this.name = name;
        this.photoPath = photoPath;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getDistanceTo(@NonNull Location location){
        //double radioTierra = 3958.75;//en millas
        double radioTierra = 6371;//en kilómetros
        double dLat = Math.toRadians(location.getLatitude() - latitude);
        double dLng = Math.toRadians(location.getLongitude() - longitude);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(location.getLatitude()));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));

        return radioTierra * va2;
    }
}
