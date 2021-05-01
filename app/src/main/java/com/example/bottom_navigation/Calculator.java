package com.example.bottom_navigation;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

public class Calculator extends Fragment {

    private View view;
    private Spinner spinner,spinner2;
    private TextView tv_result,tv_result2;


    public Calculator() {
        // Required empty public constructor
    }

    public static Calculator newInstance() {return new Calculator(); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calculator, container, false);

        spinner = (Spinner)view.findViewById(R.id.spinner);
        spinner2 = (Spinner)view.findViewById(R.id.spinner2);

        tv_result = (TextView)view.findViewById(R.id.tv_result);
        tv_result2 = (TextView)view.findViewById(R.id.tv_result2) ;



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_result.setText(parent.getItemAtPosition(position).toString());
                ((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_result2.setText(parent.getItemAtPosition(position).toString());
                ((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;
    }
}