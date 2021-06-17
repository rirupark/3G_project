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


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_ppt, container, false);
        getppt();

        return view;
    }

    void getppt() {
        Button grade_21 = (Button) view.findViewById(R.id.grade_21);
        grade_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://1drv.ms/p/s!AuRcqUg2k-bthzeOqCjc3ySlO8Vp?e=7ja6pk"));
                startActivity(intent);
            }
        });
        Button grade_20 = (Button) view.findViewById(R.id.grade_20);
        grade_20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://1drv.ms/p/s!AuRcqUg2k-bthzm2mWY-pZfLHh5q?e=iznJEF"));
                startActivity(intent);
            }
        });
        Button grade_19 = (Button) view.findViewById(R.id.grade_19);
        grade_19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://1drv.ms/p/s!AuRcqUg2k-bthzwh-jUP57WqMYkN?e=XrKHi9"));
                startActivity(intent);
            }
        });
        Button grade_18 = (Button) view.findViewById(R.id.grade_18);
        grade_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://1drv.ms/p/s!AuRcqUg2k-bthzQEZV2GQF_TithZ?e=H6Ic1H"));
                startActivity(intent);
            }
        });
    }
}