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

public class CalAdapter extends RecyclerView.Adapter<CalAdapter.CustomViewHolder> {

    private ArrayList<User> arrayList;
    private ArrayList<UserLearn> userLearnArrayList;
    private Context context;

    public CalAdapter(ArrayList<UserLearn> userLearnArrayList, Context context) {
        this.userLearnArrayList = userLearnArrayList;
        this.context = context;
    }

    @NonNull
    @Override

    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //holder.tv_name.setText(userLearnArrayList.get(position).getClassName());
        holder.tv_credit.setText(userLearnArrayList.get(position).getCredit());


    }

    @Override
    public int getItemCount() {
        // 삼향연산자
        return (userLearnArrayList != null ? userLearnArrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_credit;
        TextView tv_area;
        CheckBox check_jeon;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_credit = itemView.findViewById(R.id.tv_credit);


        }
    }
}
