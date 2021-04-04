package com.example.angelbiker.domain.DB.modelos.location;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "location_table")
public class LocationModel {

    public static final String LOCATION_NAME = "locationName";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "latitude")
    private double latitude;

    @NonNull
    @ColumnInfo(name = "longitude")
    private double longitude;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "photoPath")
    private String photoPath;

    @ColumnInfo(name = "category")
    private CategoryModel categoryModel;

    public LocationModel(int id, double latitude, double longitude, @NonNull String name, @NonNull String description, @NonNull String photoPath, CategoryModel categoryModel) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.description = description;
        this.photoPath = photoPath;
        this.categoryModel = categoryModel;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public int getId() {
        return id;
    }
}
