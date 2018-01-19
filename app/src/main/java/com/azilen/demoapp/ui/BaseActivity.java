package com.azilen.demoapp.ui;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutXML());

        initImpl();
    }

    @LayoutRes
    protected abstract int getLayoutXML();

    protected abstract void initImpl();
}
