package com.example.angelbiker.domain.DB.modelos.location;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")
public class CategoryModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "category")
    private final String category;

    public CategoryModel(int id, @NonNull String category) {
        this.id = id;
        this.category = category;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return category;
    }
}
