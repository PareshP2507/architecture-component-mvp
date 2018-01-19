package com.azilen.demoapp.ui.room;

import android.app.Activity;
import android.content.Intent;

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
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
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
        db.destroy();
    }

    void processActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_ADD_USER) {
            if (resultCode == Activity.RESULT_OK) {
                String fName = data.getStringExtra(Const.ARG_EX_F_NAME);
                mView.onUserInserted(fName);
            }
        }
    }

    void gotoActivity(Class<?> destination) {
        ((Activity) mView.getContext()).startActivityForResult(
                new Intent(mView.getContext(), destination), REQ_ADD_USER
        );
    }
}
