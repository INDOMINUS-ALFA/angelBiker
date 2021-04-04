package com.example.angelbiker.domain.DB.modelos.location;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(LocationModel locationModel);

    @Query("DELETE FROM location_table")
    void deleteAll();

    @Query("DELETE FROM location_table where id = :locationModelID")
    void deleteLocation(int locationModelID);

    @Query("SELECT name, photoPath, latitude, longitude FROM location_table")
    LiveData<List<LocationMinimal>> getLocationsMinimal();

    @Query("SELECT name, photoPath, latitude, longitude FROM location_table where category = :category")
    LiveData<List<LocationMinimal>> getLocationsMinimalByCategory(CategoryModel category);

    @Query("SELECT name, photoPath, latitude, longitude FROM location_table where name like '%' || :name || '%'")
    LiveData<List<LocationMinimal>> getLocationsMinimalByName(String name);

    @Query("SELECT * FROM location_table WHERE name = :name")
    LocationModel getLocationSingle(String name);
}
