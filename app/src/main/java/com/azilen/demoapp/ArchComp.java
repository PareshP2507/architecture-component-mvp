package com.azilen.demoapp;

import android.app.Application;

import com.azilen.demoapp.ui.room.db.AppDatabase;

import timber.log.Timber;

/**
 * Created by paresh on 11-01-2018
 */

public class ArchComp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }

    private void initApp() {
        plantTimber();
        AppDatabase.getInstance(this);
    }

    private void plantTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
