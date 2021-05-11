package com.example.bottom_navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PassgraSelec extends Fragment {


    public static Fragment newinstance() {
        return new PassgraSelec();
    }

    public PassgraSelec(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passgra_selec,null);

        return view;

    }
}
