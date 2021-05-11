package com.example.bottom_navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class PassgraEss extends Fragment {


    public static Fragment newinstance() {
        return new PassgraEss();
    }

    public PassgraEss(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passgra_ess,null);
        ImageButton btn_back = (ImageButton)view.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Passgraduation.newinstance());
            }
        });

        return view;

    }
}