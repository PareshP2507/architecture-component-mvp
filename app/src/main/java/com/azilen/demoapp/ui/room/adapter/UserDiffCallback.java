package com.azilen.demoapp.ui.room.adapter;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.azilen.demoapp.ui.room.db.User;

import java.util.List;

/**
 * Created by paresh on 30-01-2018
 */

public class UserDiffCallback extends DiffUtil.Callback {

    private List<User> oldList;
    private List<User> newList;

    UserDiffCallback(List<User> oldList, List<User> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getUid() == newList.get(newItemPosition).getUid();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
