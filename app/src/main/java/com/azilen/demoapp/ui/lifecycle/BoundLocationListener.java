package com.azilen.demoapp.ui.lifecycle;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import timber.log.Timber;

/**
 * Created by paresh on 12-01-2018
 */

class BoundLocationListener implements LifecycleObserver {

    private final Context mContext;
    private LocationManager mLocationManager;
    private final LocationListener mListener;

    static void bind(Context mContext, LifecycleOwner lifecycleOwner,
                     LocationListener mListener) {
        new BoundLocationListener(mContext, lifecycleOwner, mListener);
    }

    private BoundLocationListener(Context mContext, LifecycleOwner lifecycleOwner,
                                  LocationListener mListener) {
        this.mContext = mContext;
        this.mListener = mListener;
        lifecycleOwner.getLifecycle().addObserver(this);
    }

    @SuppressLint("MissingPermission")
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void addLocationListener() {
        mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (null != mLocationManager) {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mListener);
            Timber.d("LocationManager: requesting location updates...");
        } else {
            Timber.e("LocationManager addLocationListener(): NULL");
        }

        Location lastLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (lastLocation != null) {
            mListener.onLocationChanged(lastLocation);
        }
    }

    @SuppressLint("MissingPermission")
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void removeLocationUpdates() {
        if (null != mLocationManager) {
            mLocationManager.removeUpdates(mListener);
            mLocationManager = null;
            Timber.d("LocationManager: removing location updates...");
        } else {
            Timber.d("LocationManager removeLocationUpdates(): NULL...");
        }
    }
}
