package com.azilen.demoapp.ui.main;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.azilen.demoapp.R;
import com.azilen.demoapp.ui.BaseActivity;
import com.azilen.demoapp.ui.lifecycle.LifeCycleAwareActivity;
import com.azilen.demoapp.ui.room.UserDataActivity;

/**
 * Created by paresh on 11-01-2018
 */

public class MainActivity extends BaseActivity {

    private String[] items = {
            "LifeCycle-Aware Component",
            "Room Persistence",
    };

    @Override
    protected int getLayoutXML() {
        return R.layout.activity_main;
    }

    @Override
    protected void initImpl() {
        RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );
        mRecyclerView.setAdapter(new ItemAdapter(items) {
            @Override
            void onItemClick(int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, LifeCycleAwareActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, UserDataActivity.class));
                        break;
                }
            }
        });
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}
