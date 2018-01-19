package com.azilen.demoapp.ui.room.adapter;

import android.support.v7.widget.RecyclerView;

import com.azilen.demoapp.databinding.RowUserBinding;
import com.azilen.demoapp.ui.room.db.User;

/**
 * Created by paresh on 18-01-2018
 */

class UserViewHolder extends RecyclerView.ViewHolder {

    private RowUserBinding mBinding;

    public UserViewHolder(RowUserBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
    }

    public void bind(User user) {
        mBinding.setUser(user);
        mBinding.executePendingBindings();
    }
}
