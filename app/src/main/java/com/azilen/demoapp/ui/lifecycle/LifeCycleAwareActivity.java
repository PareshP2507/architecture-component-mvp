package com.azilen.demoapp.ui.lifecycle;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.azilen.demoapp.R;
import com.azilen.demoapp.ui.BaseActivity;

import timber.log.Timber;

/**
 * Created by paresh on 12-01-2018
 */

public class LifeCycleAwareActivity extends BaseActivity implements MyLocationListener.SimpleLocationListener {

    private MyLocationListener myLocationListener;
    private TextView tvLocation;

    private static final int REQ_LOCATION_PERMISSION = 1;

    @Override
    protected int getLayoutXML() {
        return R.layout.activity_lifecycle_aware;
    }

    @Override
    protected void initImpl() {
        tvLocation = findViewById(R.id.tvLocation);

        myLocationListener = new MyLocationListener(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQ_LOCATION_PERMISSION);
        } else {
            bindLocationListener();
        }
    }

    private void bindLocationListener() {
        BoundLocationListener.bind(this, this, myLocationListener);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (null != location) {
            tvLocation.setText(getString(R.string.location_change,
                    String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude())));
        }
    }

    @Override
    public void onProviderStatusChanged(String provider, int status, Bundle extras) {
        Timber.d("## ProviderStatusChanged %s, %d, %s", provider, status, extras);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            bindLocationListener();
        } else {
            Snackbar.make(tvLocation, "This sample requires Location access", Snackbar.LENGTH_INDEFINITE);
        }
    }
}
