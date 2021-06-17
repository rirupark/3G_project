package com.example.bottom_navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Ppt extends Fragment {

    private View view;

    public Ppt() {
        // Required empty public constructor
    }
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static Ppt newInstance(String param1, String param2) {
        Ppt fragment = new Ppt();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ppt, container, false);


        Button grade_21= (Button) v.findViewById(R.id.grade_21);
        grade_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://1drv.ms/p/s!AuRcqUg2k-bthzeOqCjc3ySlO8Vp?e=7ja6pk"));
                startActivity(intent);
            }
        });
        Button grade_20 = (Button) v.findViewById(R.id.grade_20);
        grade_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://1drv.ms/p/s!AuRcqUg2k-bth2jr6_IMqvqHDN38?e=okv1kN"));
                startActivity(intent);
            }
        });
        Button grade_19 = (Button) v.findViewById(R.id.grade_19);
        grade_19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://1drv.ms/p/s!AuRcqUg2k-bthzwh-jUP57WqMYkN?e=XrKHi9"));
                startActivity(intent);
            }
        });
        Button grade_18 = (Button) v.findViewById(R.id.grade_18);
        grade_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://1drv.ms/p/s!AuRcqUg2k-bthzQEZV2GQF_TithZ?e=H6Ic1H"));
                startActivity(intent);
            }
        });
        return v;
    }
}