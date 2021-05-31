package com.example.bottom_navigation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> implements OnCustomItemClickListener{

    private ArrayList<User> arrayList;
    private Context context;
    OnCustomItemClickListener listener;

    public CustomAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override

    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view, this);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final User user = arrayList.get(position);
        holder.tv_name.setText(arrayList.get(position).getName());
        holder.tv_credit.setText(String.valueOf(arrayList.get(position).getCredit()));
    }

    @Override
    public int getItemCount() {
        // 삼향연산자
        return (arrayList != null ? arrayList.size() : 0);
    }

    @Override
    public void onItemClick(CustomViewHolder holder, View view, int position) {

        if(listener != null) {
            listener.onItemClick(holder,view,position);
        }

    }


    public void setOnItemClickListener(OnCustomItemClickListener listener) {
        this.listener = listener;
    }
    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_credit;
        TextView tv_area;
        ImageButton btn_checked;
        ImageButton btn_noncheck;

        public CustomViewHolder(View itemView, final OnCustomItemClickListener listener) {
            super(itemView);
            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_credit = itemView.findViewById(R.id.tv_credit);
            this.btn_checked = itemView.findViewById(R.id.checkbox_check);
            this.btn_noncheck = itemView.findViewById(R.id.checkbox_blank);


            // 리사이클러뷰 아이템 클릭 이벤트.
            btn_noncheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Recyclerview_check", "position = "+ getAdapterPosition());
                    btn_noncheck.setVisibility(View.INVISIBLE);
                    btn_checked.setVisibility(View.VISIBLE);
                    int position = getAdapterPosition();
                }
            });

            // 리사이클러뷰 아이템 클릭 이벤트.
            btn_checked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Recyclerview_nocheck", "position = "+ getAdapterPosition());
                    btn_checked.setVisibility(View.INVISIBLE);
                    btn_noncheck.setVisibility(View.VISIBLE);

                }
            });

        }

    }

}