package com.example.angelbiker.domain.DB.modelos.location;

import androidx.room.ColumnInfo;

public class CategoryAndCountModel {
    private CategoryModel category;

    @ColumnInfo(name = "count(*)")
    private Integer count;

    public CategoryAndCountModel(CategoryModel category, Integer count) {
        this.category = category;
        this.count = count;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
