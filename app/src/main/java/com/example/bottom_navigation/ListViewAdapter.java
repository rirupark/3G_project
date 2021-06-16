package com.example.bottom_navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.protobuf.StringValue;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Listitem> listItems = new ArrayList<Listitem>();
    List<UserLearn> data=new ArrayList<UserLearn>();
    private OnDeleteClickListener mListener;

    public void clear() {
        listItems.clear();
    }

    public interface OnDeleteClickListener{
        void onDelete(View v, int pos);
    }

    public ListViewAdapter(Context context, OnDeleteClickListener listener){
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // item.xml 레이아웃을 inflate해서 참조획득
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_finish, parent, false);
        }

        // item.xml 의 참조 획득
        TextView txt_title = (TextView)convertView.findViewById(R.id.txt_title);
        TextView txt_sub = (TextView)convertView.findViewById(R.id.txt_sub);
        TextView txt_credit = (TextView)convertView.findViewById(R.id.txt_credit);
        Button btn_delete = (Button)convertView.findViewById(R.id.btn_delete);

        Listitem listItem = listItems.get(position);

        // 가져온 데이터를 텍스트뷰에 입력
        txt_title.setText(listItem.getTitle());
        txt_sub.setText(listItem.getSub());
        txt_credit.setText(listItem.getCredit());


        // 리스트 아이템 삭제
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDelete(view, position);
            }
        });

        return convertView;
    }

    public void addItem(String title, String sub, int credit){
        Listitem listItem = new Listitem();

        listItem.setTitle(title);
        listItem.setSub(sub);
        listItem.setCredit(String.valueOf(credit));

        listItems.add(listItem);
    }



    public void removeItem(int pos){
        listItems.remove(pos);
        notifyDataSetChanged();
    }
}