package com.example.bottom_navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Checklist extends Fragment {
    public static Checklist newinstance(){    //////모든 프레그먼트에 newinstance메소드가 있어야함..!!
        return new Checklist();
    }

    public Checklist(){

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checklist,null);

        Button btn_go_gyoyang = (Button)view.findViewById(R.id.btn_go_gyoyang);
        btn_go_gyoyang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Gyuyang.newinstance());
            }
        });

       Button btn_go_jeongong = (Button)view.findViewById(R.id.btn_go_jeongong);
        btn_go_jeongong.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).replaceFragment(Jeongong.newinstance());

            }
        });

        Button btn_go_jolup = (Button)view.findViewById(R.id.btn_go_jolup);
        btn_go_jolup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).replaceFragment(Passgraduation.newinstance());

            }
        });

        return view;


    }


}

