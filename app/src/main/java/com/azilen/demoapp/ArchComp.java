package com.azilen.demoapp;

import android.app.Application;

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
    }

    private void plantTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
