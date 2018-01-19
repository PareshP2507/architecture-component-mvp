package com.azilen.demoapp.ui.room.adapter;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import com.azilen.demoapp.R;
import com.azilen.demoapp.ui.room.db.User;

/**
 * Created by paresh on 18-01-2018
 */

public class DataBinder {

    @BindingAdapter("android:name")
    public static void setName(TextView tv, User user) {
        String name = (user.getFirstName() != null ? user.getFirstName() : "")
                .concat(" ")
                .concat(user.getLastName() != null ? user.getLastName() : "");
        tv.setText(name);
    }

    @BindingAdapter("android:userId")
    public static void setAge(TextView tv, User user) {
        tv.setText(tv.getContext().getString(R.string.person_id, user.getUid()));
    }
}
