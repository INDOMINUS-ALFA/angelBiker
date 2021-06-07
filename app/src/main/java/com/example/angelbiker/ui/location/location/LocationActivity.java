package com.example.angelbiker.ui.location.location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.example.angelbiker.R;
import com.example.angelbiker.databinding.ActivityLocationBinding;
import com.example.angelbiker.domain.DB.modelos.location.LocationModel;
import com.example.angelbiker.ui.viewModels.LocationViewModel;

public class LocationActivity extends AppCompatActivity {

    private LocationViewModel locationViewModel;
    private LocationModel locationModel;
    LocationManager locationManager;
    Button btn_Track;
    public Criteria criteria;
    public String bestProvider;
    public double latitude;
    public double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();
        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(bestProvider);
        latitude = location.getLatitude();
        longitude = location.getLongitude();


        ActivityLocationBinding activityLocationBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_location);

        locationViewModel = new LocationViewModel(
                this.getApplication(),
                getIntent().getStringExtra(LocationModel.LOCATION_NAME));

        activityLocationBinding.setViewModel(locationViewModel);

        btn_Track = findViewById(R.id.btn_track);


        btn_Track.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                //Get value from edit text


                String sSource = ( latitude + "," + longitude).toString().trim();
                String sDestination = (locationViewModel.getLocationModel().getLatitude() + "," + locationViewModel.getLocationModel().getLongitude()).toString().trim();

                //Check condition
                if(sSource.equals("") && sDestination.equals("")) {
                    //Where both value blank
                    Toast.makeText(getApplicationContext(), "Enter both location", Toast.LENGTH_LONG).show();
                }else {
                    //Wher both value fill
                    //Daisplay track
                    DisplayTrack(sSource, sDestination);
                }
            }
        });
    }

    public void openInMapsButton(View view){
        //locationViewModel.openInGoogleMaps(this);
        locationViewModel.openInMap(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_location, menu);
        MenuItem deleteItem = menu.findItem(R.id.app_bar_delete);

        deleteItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                locationViewModel.deleteLocation();
                finish();
                //TODO sure yes no dialog
                return false;
            }
        });

        return true;
    }

    private void DisplayTrack(String sSource, String sDestination) {
        //If the device does not have a map installed then redirect it to play storte
        try{
            //When google map is instale
            //Initialize uri
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+ sSource+ "/" + sDestination);
            //initialize intent with view
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            //Set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);
        } catch (ActivityNotFoundException e){
            //When google map is not installed
            //Initialice uri
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            //Initialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            //Set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);
        }
    }
}