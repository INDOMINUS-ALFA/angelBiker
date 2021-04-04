package com.example.angelbiker.ui.viewModels;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;

import com.example.angelbiker.domain.DB.LocationRoomDatabase;
import com.example.angelbiker.domain.DB.modelos.location.LocationDao;
import com.example.angelbiker.domain.DB.modelos.location.LocationModel;
import com.example.angelbiker.ui.location.map.MapActivity;

public class LocationViewModel extends AndroidViewModel {

    private LocationModel locationModel;
    private LocationRoomDatabase db;

    public LocationViewModel(@NonNull Application application, String locationName) {
        super(application);
        db = LocationRoomDatabase.getDatabase(application);
        LocationDao locationDao = db.locationDao();
        LocationRoomDatabase.getDatabaseWriteExecutor().execute(() -> {
            locationModel = locationDao.getLocationSingle(locationName);
        });
    }

    public LocationModel getLocationModel() {
        return locationModel;
    }

    public void setLocationModel(LocationModel locationModel) {
        this.locationModel = locationModel;
    }

    public void deleteLocation(){
        LocationDao locationDao = db.locationDao();
        LocationRoomDatabase.getDatabaseWriteExecutor().execute(() -> {
            locationDao.deleteLocation(locationModel.getId());
        });
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void openInGoogleMaps(Context context) {
        Uri gmmIntentUri = Uri.parse("geo:0,0"
                + "?q=" + locationModel.getLatitude() + "," + locationModel.getLongitude()
                + "(" + locationModel.getName() + ")");
        Intent googleMapsIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        googleMapsIntent.setPackage("com.google.android.apps.maps");
        if (googleMapsIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(googleMapsIntent);
        }
    }

    public void openInMap(Context context){
        Intent mapIntent = new Intent(context, MapActivity.class);
        mapIntent.putExtra("latitude", locationModel.getLatitude());
        mapIntent.putExtra("longitude", locationModel.getLongitude());
        context.startActivity(mapIntent);
    }

    @BindingAdapter("src")
    public static void bindImage(Context context, ImageView imageView, String src) {
        Uri uri = Uri.parse(src);
        imageView.setImageURI(uri);
    }
}
