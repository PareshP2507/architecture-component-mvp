package com.azilen.demoapp.ui.room.add;

import com.azilen.demoapp.ui.room.db.AppDatabase;
import com.azilen.demoapp.ui.room.db.User;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by paresh on 19-01-2018
 */

class AddUserPresenter implements AddUserContract.EspressoPresenter {

    private AddUserContract.EspressoView mView;
    private AppDatabase db;
    private CompositeDisposable compositeDisposable;

    AddUserPresenter(AddUserContract.EspressoView mView, AppDatabase db) {
        this.mView = mView;
        this.db = db;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void addUser(User... user) {
        if (null == user) return;
        db.userDao().insertAll(user);
        mView.onUserAdded(user);
    }

    @Override
    public void clear() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }
}
