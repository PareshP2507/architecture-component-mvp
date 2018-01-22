package com.azilen.demoapp.ui.room;

import android.content.Context;

import com.azilen.demoapp.ui.room.db.User;

import java.util.List;

/**
 * Created by paresh on 19-01-2018
 */

class UserDataContract {

    public interface DataView {

        void updateProgressVisibility(boolean isVisible);

        void updateNoDataVisibility(boolean isVisible);

        void onUserInserted(String firstName, boolean isUpdate);

        void onUserRetrieval(List<User> users);

        void onError(String message);

        Context getContext();
    }
}
