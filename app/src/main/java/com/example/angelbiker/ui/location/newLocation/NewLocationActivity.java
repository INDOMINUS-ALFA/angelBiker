package com.example.angelbiker.ui.location.newLocation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.example.angelbiker.R;
import com.example.angelbiker.databinding.ActivityNewLocationActivityBinding;
import com.example.angelbiker.domain.DB.location.LocationManager;
import com.example.angelbiker.domain.DB.modelos.location.CategoryModel;
import com.example.angelbiker.ui.dialogs.categories.CategoriesDialogFragment;
import com.example.angelbiker.ui.dialogs.categories.CategorySelectionManager;
import com.example.angelbiker.ui.dialogs.categories.NewCategoryDialogFragment;
import com.example.angelbiker.ui.viewModels.NewLocationViewModel;

import java.io.File;
import java.util.Objects;

import static com.example.angelbiker.ui.viewModels.NewLocationViewModel.REQUEST_TAKE_PHOTO;


public class NewLocationActivity extends AppCompatActivity implements LocationManager.LocationManagerHandler, CategorySelectionManager {

    private ActivityNewLocationActivityBinding activityNewLocationActivityBinding;
    private NewLocationViewModel newLocationViewModel;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityNewLocationActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_new_location_activity);

        newLocationViewModel = new NewLocationViewModel(this.getApplication());
        activityNewLocationActivityBinding.setViewModel(newLocationViewModel);

        imageView = findViewById(R.id.imageView);

        setSpinner();
    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        LocationManager.getLocationCurrent(this);
    }

    private void setSpinner() {
        Spinner spinner = findViewById(R.id.spinner);

        newLocationViewModel.getFirstsCategories().observe(this, categoryModels -> {
            String[] array;
            array = new String[categoryModels.size()];


            for (int i = 0; i < array.length; i++) {
                array[i] = categoryModels.get(i).getCategory();
            }

            if (array.length > 3) {
                String[] copiedArray = array;
                array = new String[copiedArray.length + 1];
                System.arraycopy(copiedArray, 0, array, 0, copiedArray.length);
                array[array.length-1] = getString(R.string.more);
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(NewLocationActivity.this, android.R.layout.simple_list_item_1, array);
            spinner.setAdapter(arrayAdapter);
        });

        Activity activity = this;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            private int previousSelected;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position < 4) {
                    newLocationViewModel.setCategory(Objects.requireNonNull(newLocationViewModel.getFirstsCategories().getValue()).get(position));
                    previousSelected = spinner.getSelectedItemPosition();
                } else {
                    spinner.setSelection(previousSelected);
                    CategoriesDialogFragment.newInstance(activity, newLocationViewModel.getAllCategories(), previousSelected).show(getSupportFragmentManager(), "");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onCategorySelected(CategoryModel categoryModel) {
        newLocationViewModel.setFirstCategory(categoryModel);
        setSpinner();
    }

    public void onCategoryAddButtonClicked(View view) {
        NewCategoryDialogFragment.newInstance(this).show(getSupportFragmentManager(), "");
    }

    public void onTakePictureButtonClicked(View view) {
        newLocationViewModel.takePicture(this);
    }

    public void onCheckButtonClicked(View view) {
        String information = newLocationViewModel.informationValid();
        if (information.equals(NewLocationViewModel.INFORMATION_VALID)) {
            newLocationViewModel.saveLocationOnDatabase(this.getApplication());
            finish();
        } else {
            Toast.makeText(this, information, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                File f = new File(newLocationViewModel.getPhotoPath());
                Uri contentUri = Uri.fromFile(f);
                imageView.setImageURI(contentUri);
            } else {
                newLocationViewModel.setPhotoPath(null);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LocationManager.REQUEST_CODE_LOCATION:
                LocationManager.onRequestedLocationPermissionsResult(this);
                break;
            case 1:
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        newLocationViewModel.setLocation(location);
        activityNewLocationActivityBinding.invalidateAll();
    }

    @Override
    public void onLocationPermissionDenied() {

    }
}