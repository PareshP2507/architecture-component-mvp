package com.azilen.demoapp.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.azilen.demoapp.R;

/**
 * Created by paresh on 12-01-2018
 */

abstract class ItemHolder extends RecyclerView.ViewHolder {

    private TextView tvName;

    ItemHolder(View root) {
        super(root);
        tvName = root.findViewById(R.id.tvItem);
        root.setOnClickListener(v -> onRowClick(getAdapterPosition()));
    }

    void bind(String item) {
        tvName.setText(item);
    }

    abstract void onRowClick(int position);
}
