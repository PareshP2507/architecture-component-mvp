package com.azilen.demoapp.ui.room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.azilen.demoapp.R;
import com.azilen.demoapp.ui.BaseActivity;
import com.azilen.demoapp.ui.room.adapter.RecyclerViewAdapter;
import com.azilen.demoapp.ui.room.add.AddUserActivity;
import com.azilen.demoapp.ui.room.db.AppDatabase;
import com.azilen.demoapp.ui.room.db.User;
import com.azilen.demoapp.utils.Const;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paresh on 18-01-2018
 */

public class UserDataActivity extends BaseActivity implements UserDataContract.DataView, View.OnClickListener {

    private UserDataPresenter rPresenter;

    private FloatingActionButton fabAdd;
    private ProgressBar mProgress;
    private RecyclerViewAdapter adapter;
    private View vNotFound;

    private List<User> mData = new ArrayList<>();

    @Override
    protected int getLayoutXML() {
        return R.layout.activity_user_data;
    }

    @Override
    protected void initImpl() {
        rPresenter = new UserDataPresenter(this, AppDatabase.getInstance(this));

        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());
        fabAdd = findViewById(R.id.fabAdd);
        mProgress = findViewById(R.id._progress);
        vNotFound = findViewById(R.id.notFound);
        RecyclerView mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new RecyclerViewAdapter(mData) {
            @Override
            protected void onRowClick(User user, int position) {
                handleRecyclerViewRowClick(user);
            }
        };
        mRecyclerView.setAdapter(adapter);
        fabAdd.setOnClickListener(this);

        rPresenter.fetchAll();
    }

    private void handleRecyclerViewRowClick(User user) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Const.ARG_EX_USER, user);
        rPresenter.gotoActivity(AddUserActivity.class, bundle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabAdd:
                rPresenter.gotoActivity(AddUserActivity.class, new Bundle());
                break;
        }
    }

    @Override
    public void updateProgressVisibility(boolean isVisible) {
        mProgress.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void updateNoDataVisibility(boolean isVisible) {
        vNotFound.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onUserInserted(String fName, boolean isUpdate) {
        Snackbar.make(fabAdd, "User " + fName + " is " +(isUpdate ? "updated." : "inserted."),
                Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onUserRetrieval(List<User> users) {
        adapter.refreshItems(users);
    }

    @Override
    public void onError(String message) {
        Snackbar.make(fabAdd, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        rPresenter.onClear();
        if (adapter != null) {
            adapter.clear();
        }
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        rPresenter.processActivityResult(requestCode, resultCode, data);
    }
}
