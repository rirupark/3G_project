package com.example.bottom_navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PassAdapter extends RecyclerView.Adapter<PassAdapter.CustomViewHolder> {

    private ArrayList<Pass> arrayList;


    public PassAdapter(ArrayList<Pass> arrayList) {
        this.arrayList = arrayList;

    }

    @NonNull
    @Override

    public PassAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pass, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        holder.tv_pass.setText(arrayList.get(position).getTv_pass());
        holder.tv_pass1.setText(arrayList.get(position).getTv_pass1());

    }

    @Override
    public int getItemCount() {
        // 삼향연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_pass;
        private TextView tv_pass1;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_pass = itemView.findViewById(R.id.tv_pass);
            this.tv_pass1 = itemView.findViewById(R.id.tv_pass1);


        }
    }
}
