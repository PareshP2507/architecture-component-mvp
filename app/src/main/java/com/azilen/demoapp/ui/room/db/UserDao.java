package com.azilen.demoapp.ui.room.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by paresh on 18-01-2018
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM user_master")
    Flowable<List<User>> getAll();

    @Insert
    void insertAll(User... users);

    @Update
    void update(User users);

    @Delete
    void deleteUser(User user);
}
