package com.azilen.demoapp.ui.room.add;

import com.azilen.demoapp.ui.room.db.User;

/**
 * Created by paresh on 19-01-2018
 */

public class AddUserContract {

    public interface EspressoView {

        void onError(String message);

        void onUserAdded(User... user);
    }

    public interface EspressoPresenter {

        void addUser(User... user);

        void clear();
    }
}
