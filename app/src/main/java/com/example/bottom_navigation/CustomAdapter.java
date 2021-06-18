package com.example.bottom_navigation;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> implements OnCustomItemClickListener {

    private ArrayList<User> arrayList;
    private Context context;
    OnCustomItemClickListener listener;
    private ArrayList<String> listData = new ArrayList<String>();
    private FirebaseAuth auth; // 파베 인증 객체
    private DatabaseReference mDatabase;
    private Integer check = 0;

    public CustomAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @Override

    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
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

        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }

    }


    public void setOnItemClickListener(OnCustomItemClickListener listener) {
        this.listener = listener;
    }

    public void removeItem2(int pos) {
        arrayList.remove(pos);
        notifyDataSetChanged();
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id;
        TextView tv_name;
        TextView tv_credit;
        TextView tv_area;
        ImageButton btn_checked;
        ImageButton btn_noncheck;
        CheckBox checkbox;

        public CustomViewHolder(View itemView) {
            super(itemView);

            this.tv_name = itemView.findViewById(R.id.tv_name);
            this.tv_credit = itemView.findViewById(R.id.tv_credit);
            this.btn_checked = itemView.findViewById(R.id.checkbox_check);
            this.btn_noncheck = itemView.findViewById(R.id.checkbox_blank);



            auth = FirebaseAuth.getInstance();
            mDatabase = FirebaseDatabase.getInstance().getReference("UserInfo");
            FirebaseUser firebaseUser = auth.getCurrentUser();

//
            btn_noncheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Recyclerview_check", "position = " + getAdapterPosition());
                    btn_noncheck.setVisibility(View.INVISIBLE);
                    btn_checked.setVisibility(View.VISIBLE);

                    Log.e("user", firebaseUser.getUid());

                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        // 데이터 리스트로부터 아이템 데이터 참조.
                        User item = arrayList.get(pos);

                        Log.e("tv_name", item.getName());


                        mDatabase.child(firebaseUser.getUid()).child("finish").child(item.getName()).child("className").setValue(item.getName());
                        mDatabase.child(firebaseUser.getUid()).child("finish").child(item.getName()).child("credit").setValue(item.getCredit());
                        if(mDatabase.child(firebaseUser.getUid()).child("finish").getKey().contains(item.getName())) {
                            btn_noncheck.setVisibility(View.GONE);
                            btn_checked.setVisibility(View.VISIBLE);
                        }


                    }



                }
            });


            btn_checked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Recyclerview_nocheck", "position = " + getAdapterPosition());
                    btn_checked.setVisibility(View.INVISIBLE);
                    btn_noncheck.setVisibility(View.VISIBLE);

                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        // 데이터 리스트로부터 아이템 데이터 참조.
                        User item = arrayList.get(pos);

                        Log.e("tv_name", item.getName());

                        DatabaseReference hopperRef = mDatabase.child(firebaseUser.getUid()).child("finish").child(item.getName());
                        hopperRef.removeValue();
                    }

                }
            });

        }
    }

}
