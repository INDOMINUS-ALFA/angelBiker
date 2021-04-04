package com.example.angelbiker.domain.DB.modelos.location;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static CategoryModel fromCategoryString(String category) {
        return  category == null ? null : new CategoryModel(0, category);
    }

    @TypeConverter
    public static String categoryToString(CategoryModel categoryModel) {
        return categoryModel == null ? null : categoryModel.getCategory();
    }
}
