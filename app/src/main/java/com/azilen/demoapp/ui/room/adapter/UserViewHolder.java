package com.azilen.demoapp.ui.room.adapter;

import android.support.v7.widget.RecyclerView;

import com.azilen.demoapp.databinding.RowUserBinding;
import com.azilen.demoapp.ui.room.db.User;

/**
 * Created by paresh on 18-01-2018
 */

abstract class UserViewHolder extends RecyclerView.ViewHolder {

    private RowUserBinding mBinding;

    UserViewHolder(RowUserBinding mBinding) {
        super(mBinding.getRoot());
        this.mBinding = mBinding;
        mBinding.getRoot().setOnClickListener(v -> onItemClick(getAdapterPosition()));
    }

    void bind(User user) {
        mBinding.setUser(user);
        mBinding.executePendingBindings();
    }

    abstract void onItemClick(int position);
}
