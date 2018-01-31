package com.azilen.demoapp.ui.room.adapter;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.azilen.demoapp.R;
import com.azilen.demoapp.databinding.RowUserBinding;
import com.azilen.demoapp.ui.room.db.User;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by paresh on 18-01-2018
 */

public abstract class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> mData;
    private Deque<List<User>> pendingItems = new ArrayDeque<>();
    private Handler mHandler = new Handler(Looper.getMainLooper());

    protected RecyclerViewAdapter(List<User> mData) {
        this.mData = new ArrayList<>(mData);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RowUserBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.row_user, parent, false);
        return new UserViewHolder(mBinding) {
            @Override
            void onItemClick(int position) {
                onRowClick(mData.get(position), position);
            }
        };
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

    public void refreshItems(List<User> items) {
        pendingItems.add(items); // Adding update to queue
        if (pendingItems.size() > 1) {
            return;
        }
        updateInternalItems(items);
    }

    /**
     * Dispatching updates to adapter
     * @param diffResult -> result of difference need to be pushed
     * @param newItems -> Updated list of items
     */
    private void dispatchUpdates(DiffUtil.DiffResult diffResult, List<User> newItems) {
        diffResult.dispatchUpdatesTo(this);
        mData.clear();
        mData.addAll(newItems);
    }

    /**
     * Pushing heavy lifting code to background thread
     * @param items -> New list of items
     */
    private void updateInternalItems(List<User> items) {
        new Thread(() -> {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(
                    new UserDiffCallback(mData, items), true);
            mHandler.post(() -> {
                pendingItems.remove(items);
                dispatchUpdates(diffResult, items);
            });
        }).start();
    }

    public void clear() {
        pendingItems.clear();
        mHandler.removeCallbacksAndMessages(null);
    }

    protected abstract void onRowClick(User user, int position);
}
