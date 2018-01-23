package com.azilen.demoapp.ui.room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.azilen.demoapp.ui.room.db.AppDatabase;
import com.azilen.demoapp.utils.Const;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by paresh on 18-01-2018
 */

class UserDataPresenter {

    private UserDataContract.DataView mView;
    private AppDatabase db;
    private CompositeDisposable compositeDisposable;

    private static final int REQ_ADD_USER = 1;

    UserDataPresenter(UserDataContract.DataView mView, AppDatabase db) {
        this.mView = mView;
        this.db = db;
        this.compositeDisposable = new CompositeDisposable();
    }

    void fetchAll() {
        mView.updateProgressVisibility(true);
        mView.updateNoDataVisibility(false);
        Disposable d = db.userDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    mView.updateNoDataVisibility(users.size() == 0);
                    mView.onUserRetrieval(users);
                    mView.updateProgressVisibility(false);
                });
        compositeDisposable.add(d);
    }

    void onClear() {
        if (null != compositeDisposable && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    void processActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_ADD_USER) {
            if (resultCode == Activity.RESULT_OK) {
                String fName = data.getStringExtra(Const.ARG_EX_F_NAME);
                boolean isUpdated = data.getBooleanExtra(Const.ARG_EX_IS_UPDATE, false);
                mView.onUserInserted(fName, isUpdated);
            }
        }
    }

    void gotoActivity(Class<?> destination, Bundle b) {
        ((Activity) mView.getContext()).startActivityForResult(
                new Intent(mView.getContext(), destination).putExtras(b), REQ_ADD_USER
        );
    }
}
