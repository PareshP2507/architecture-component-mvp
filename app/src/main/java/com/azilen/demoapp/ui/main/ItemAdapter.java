package com.azilen.demoapp.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azilen.demoapp.R;

/**
 * Created by paresh on 12-01-2018
 */

abstract class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String[] items;

    ItemAdapter(String[] items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.row_item, parent, false
        );
        return new ItemHolder(v) {
            @Override
            void onRowClick(int position) {
                onItemClick(position);
            }
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder) {
            ((ItemHolder) holder).bind(items[holder.getAdapterPosition()]);
        }
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    abstract void onItemClick(int position);
}
