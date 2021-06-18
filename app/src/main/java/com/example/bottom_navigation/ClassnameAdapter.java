package com.example.bottom_navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassnameAdapter extends RecyclerView.Adapter<ClassnameAdapter.ItemViewHolder> {

    private ArrayList<String> listData = new ArrayList<String>();

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        holder.onBind(listData.get(position));

    }

    @Override
    public int getItemCount() {

        return listData.size();
    }
    public void updateData(ArrayList<String> data) {
        listData.clear();
        listData.addAll(data);
        notifyDataSetChanged();
    }
    public void addItem(String string) {

        listData.add(string);
    }
    public void removeItem(int position){
        listData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listData.size());
    }



    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView1;

        ItemViewHolder(View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.tv_name);

        }

        void onBind(String data) {
            textView1.setText(data);
        }
    }
}