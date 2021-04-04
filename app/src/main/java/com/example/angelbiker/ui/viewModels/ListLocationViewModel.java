package com.example.angelbiker.ui.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.angelbiker.domain.DB.LocationRoomDatabase;
import com.example.angelbiker.domain.DB.modelos.location.CategoryDao;
import com.example.angelbiker.domain.DB.modelos.location.CategoryModel;
import com.example.angelbiker.domain.DB.modelos.location.LocationDao;
import com.example.angelbiker.domain.DB.modelos.location.LocationMinimal;
import com.example.angelbiker.ui.location.listLocation.ListLocationActivity;

import java.util.List;

public class ListLocationViewModel extends AndroidViewModel {

    private LocationRoomDatabase db;

    public ListLocationViewModel(@NonNull Application application) {
        super(application);
        db = LocationRoomDatabase.getDatabase(application);
    }

    public LiveData<List<LocationMinimal>> getLocationListByCategory(CategoryModel categoryModel) {
        return db.locationDao().getLocationsMinimalByCategory(categoryModel);
    }

    public LiveData<List<LocationMinimal>> getLocationList() {
        return db.locationDao().getLocationsMinimal();
    }

    public LiveData<List<LocationMinimal>> getLocationListByName(String name) {
        return db.locationDao().getLocationsMinimalByName(name);
    }

    public void getCategoriesAndCount(ListLocationActivity listLocationActivity) {
        CategoryDao categoryDao = db.categoryDao();
        LocationRoomDatabase.getDatabaseWriteExecutor().execute(() -> {
            listLocationActivity.onGetCategoriesAndCountQueryResult(categoryDao.getCategoriesAndCount());
        });
    }

    public void deleteTables() {
        CategoryDao categoryDao = db.categoryDao();
        LocationDao locationDao = db.locationDao();
        LocationRoomDatabase.getDatabaseWriteExecutor().execute(() -> {
            categoryDao.deleteAll();
            locationDao.deleteAll();
        });
    }
}
