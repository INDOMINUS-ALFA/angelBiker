package com.example.angelbiker.ui.viewModels;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.angelbiker.BuildConfig;
import com.example.angelbiker.R;
import com.example.angelbiker.domain.DB.LocationRoomDatabase;
import com.example.angelbiker.domain.DB.modelos.location.CategoryDao;
import com.example.angelbiker.domain.DB.modelos.location.CategoryModel;
import com.example.angelbiker.domain.DB.modelos.location.LocationDao;
import com.example.angelbiker.domain.DB.modelos.location.LocationModel;
import com.example.angelbiker.domain.storage.StorageManager;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class NewLocationViewModel extends AndroidViewModel {

    public static String INFORMATION_VALID = "Valid";
    static String INFORMATION_MISSING_NAME;
    static String INFORMATION_MISSING_DESCRIPTION;
    static String INFORMATION_MISSING_PHOTO;
    static String INFORMATION_MISSING_COORDINATES;
    static String INFORMATION_MISSING_CATEGORY;

    public static final int REQUEST_TAKE_PHOTO = 1;

    private boolean locationSetted = false;
    private double latitude, longitude;
    private String name, description;
    private String photoPath;
    private CategoryModel category;

    private LiveData<List<CategoryModel>> lastCategories;
    private List<CategoryModel> allCategories;

    public NewLocationViewModel(@NonNull Application application) {
        super(application);
        INFORMATION_MISSING_NAME = application.getString(R.string.missing_name);
        INFORMATION_MISSING_DESCRIPTION = application.getString(R.string.missing_description);
        INFORMATION_MISSING_PHOTO = application.getString(R.string.missing_photo);
        INFORMATION_MISSING_COORDINATES = application.getString(R.string.missing_coordinates);
        INFORMATION_MISSING_CATEGORY = application.getString(R.string.missing_category);

        LocationRoomDatabase db = LocationRoomDatabase.getDatabase(application);
        CategoryDao categoryDao = db.categoryDao();
        LocationRoomDatabase.getDatabaseWriteExecutor().execute(() -> {
            lastCategories = categoryDao.getLastCategories();
            allCategories = categoryDao.getCategories();
        });

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public void takePicture(Activity activity) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent

        // Create the File where the photo should go
        File photoFile = null;
        try {
            photoFile = StorageManager.createImageFile(activity);
            photoPath = StorageManager.getCurrentPhotoPath();
        } catch (IOException ex) {
            // Error occurred while creating the File

        }
        // Continue only if the File was successfully created
        if (photoFile != null) {
            Uri photoURI = FileProvider.getUriForFile(activity,
                    BuildConfig.APPLICATION_ID + ".provider",
                    photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    public String informationValid() {
        if (name == null || name.length() == 0) {
            return NewLocationViewModel.INFORMATION_MISSING_NAME;
        } else if (description == null || description.length() == 0) {
            return NewLocationViewModel.INFORMATION_MISSING_DESCRIPTION;
        } else if (photoPath == null || photoPath.length() == 0) {
            return NewLocationViewModel.INFORMATION_MISSING_PHOTO;
        } else if (!locationSetted) {
            return NewLocationViewModel.INFORMATION_MISSING_COORDINATES;
        } else if (category == null) {
            return NewLocationViewModel.INFORMATION_MISSING_CATEGORY;
        }
        return NewLocationViewModel.INFORMATION_VALID;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        if (photoPath == null) {
            File photo = new File(this.photoPath);
            photo.delete();
        }
        this.photoPath = photoPath;
    }

    public void setLocation(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        locationSetted = true;
    }

    public LocationModel getLocationItem() {
        return new LocationModel(0, latitude, longitude, name, description, photoPath, category);
    }

    public void saveLocationOnDatabase(Application application) {
        LocationModel locationModel = getLocationItem();
        LocationRoomDatabase db = LocationRoomDatabase.getDatabase(application);
        LocationDao locationDao = db.locationDao();
        LocationRoomDatabase.getDatabaseWriteExecutor().execute(() -> {
            locationDao.insert(locationModel);
        });
    }

    public LiveData<List<CategoryModel>> getFirstsCategories() {
        while (lastCategories == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return lastCategories;
    }

    public void setFirstCategory(CategoryModel categoryModel) {
        this.lastCategories.getValue().add(0, categoryModel);
        if (lastCategories.getValue().size() >= 4) {
            lastCategories.getValue().remove(lastCategories.getValue().size() - 1);
        }
        Log.i("firstCategories", this.lastCategories.getValue().toString());
    }

    public List<CategoryModel> getAllCategories() {
        return allCategories;
    }
}
