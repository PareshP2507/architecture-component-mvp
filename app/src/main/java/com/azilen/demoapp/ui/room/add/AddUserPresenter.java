package com.azilen.demoapp.ui.room.add;

import com.azilen.demoapp.ui.room.db.AppDatabase;
import com.azilen.demoapp.ui.room.db.User;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by paresh on 19-01-2018
 */

class AddUserPresenter implements AddUserContract.AddUserPresenter {

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
        Disposable disposable = Flowable.fromArray(user)
                .doOnNext(user1 -> db.userDao().insertAll(user1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user12 -> mView.onUserAdded(user12));
        compositeDisposable.add(disposable);
    }

    @Override
    public void updateUser(User user) {
        Disposable disposable = Flowable.fromArray(user)
                .doOnNext(user1 -> db.userDao().update(user1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user12 -> mView.onUserAdded(user12));
        compositeDisposable.add(disposable);
    }

    @Override
    public void clear() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }
}
