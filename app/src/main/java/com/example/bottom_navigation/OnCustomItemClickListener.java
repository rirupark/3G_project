package com.example.bottom_navigation;

import android.view.View;

public interface OnCustomItemClickListener {
    public void onItemClick(CustomAdapter.CustomViewHolder holder, View view, int position);
}
