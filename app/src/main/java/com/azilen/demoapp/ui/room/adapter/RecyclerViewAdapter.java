package com.azilen.demoapp.ui.room.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.azilen.demoapp.R;
import com.azilen.demoapp.databinding.RowUserBinding;
import com.azilen.demoapp.ui.room.db.User;

import java.util.List;

/**
 * Created by paresh on 18-01-2018
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> mData;

    public RecyclerViewAdapter(List<User> mData) {
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowUserBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_user, parent, false);
        return new UserViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof UserViewHolder) {
            ((UserViewHolder) holder).bind(mData.get(holder.getAdapterPosition()));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
