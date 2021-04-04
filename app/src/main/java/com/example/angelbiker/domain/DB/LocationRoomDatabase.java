package com.example.angelbiker.domain.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.angelbiker.domain.DB.modelos.location.CategoryDao;
import com.example.angelbiker.domain.DB.modelos.location.CategoryModel;
import com.example.angelbiker.domain.DB.modelos.location.Converters;
import com.example.angelbiker.domain.DB.modelos.location.LocationDao;
import com.example.angelbiker.domain.DB.modelos.location.LocationModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {LocationModel.class, CategoryModel.class}, version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class LocationRoomDatabase extends RoomDatabase {
    public abstract LocationDao locationDao();
    public abstract CategoryDao categoryDao();

    private static volatile LocationRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    public static LocationRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocationRoomDatabase.class, "location_database").addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static ExecutorService getDatabaseWriteExecutor() {
        return databaseWriteExecutor;
    }
}
