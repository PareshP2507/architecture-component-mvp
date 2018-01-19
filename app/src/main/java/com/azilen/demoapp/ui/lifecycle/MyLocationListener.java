package com.azilen.demoapp.ui.lifecycle;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by paresh on 12-01-2018
 */

public class MyLocationListener implements LocationListener {

    private SimpleLocationListener simpleLocationListener;

    MyLocationListener(SimpleLocationListener simpleLocationListener) {
        this.simpleLocationListener = simpleLocationListener;
    }

    @Override
    public void onLocationChanged(Location location) {
        simpleLocationListener.onLocationChanged(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        simpleLocationListener.onProviderStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    interface SimpleLocationListener {
        void onLocationChanged(Location location);
        void onProviderStatusChanged(String provider, int status, Bundle extras);
    }
}
