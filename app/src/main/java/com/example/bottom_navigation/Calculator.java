package com.example.bottom_navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Calculator extends Fragment {
    public static Calculator newinstance(){    //////모든 프레그먼트에 newinstance메소드가 있어야함..!!
        return new Calculator();
    }

    public Calculator(){

    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calculator, null);



        return view;
    }
}


/*
public class Calculator extends Fragment {

    private View view;



    public Calculator() {
        // Required empty public constructor
    }

    public static Calculator newInstance() {return new Calculator(); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calculator, container, false);


        return view;
    }
}
 */