package com.example.bottom_navigation;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Jeongong extends Fragment {

    private View view;
    private Spinner spn_jeongong,spn_grade;
    private TextView tv_result3,tv_result4;


public static Jeongong newinstance(){
    return new Jeongong();
}
public Jeongong(){

}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view =  inflater.inflate(R.layout.fragment_jeongong,container,false);

        spn_jeongong = (Spinner)view.findViewById(R.id.spn_jeongong);
        spn_grade = (Spinner)view.findViewById(R.id.spn_grade);
        tv_result3 = (TextView)view.findViewById(R.id.tv_result3);
        tv_result4 = (TextView)view.findViewById(R.id.tv_result4);


        spn_jeongong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_result3.setText(parent.getItemAtPosition(position).toString());
                ((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_result4.setText(parent.getItemAtPosition(position).toString());
                ((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

return view;
    }
}
