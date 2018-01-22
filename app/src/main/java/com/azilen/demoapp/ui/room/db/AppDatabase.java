package com.azilen.demoapp.ui.room.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by paresh on 18-01-2018
 */

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "app_db")
                    .build();
        }
        return instance;
    }

    public void destroy() {
        instance = null;
    }

    public abstract UserDao userDao();
}
