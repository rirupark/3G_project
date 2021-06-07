package com.example.bottom_navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;


public class PassgraEss extends Fragment {

    CheckBox chk_6;
    CheckBox chk_7;
    CheckBox chk_8;
    CheckBox chk_9;
    CheckBox chk_10;
    CheckBox chk_11;
    CheckBox chk_12;

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


        Context context = getActivity();
        SharedPreferences pref = context.getSharedPreferences("pref",0);
        chk_6= (CheckBox)view.findViewById(R.id.chk_6);
        chk_7= (CheckBox)view.findViewById(R.id.chk_7);
        chk_8= (CheckBox)view.findViewById(R.id.chk_8);
        chk_9= (CheckBox)view.findViewById(R.id.chk_9);
        chk_10= (CheckBox)view.findViewById(R.id.chk_10);
        chk_11= (CheckBox)view.findViewById(R.id.chk_11);
        chk_12= (CheckBox)view.findViewById(R.id.chk_12);

        chk_6.setChecked(pref.getBoolean("check",false));
        chk_7.setChecked(pref.getBoolean("check1",false));
        chk_8.setChecked(pref.getBoolean("check2",false));
        chk_9.setChecked(pref.getBoolean("check3",false));
        chk_10.setChecked(pref.getBoolean("check4",false));
        chk_11.setChecked(pref.getBoolean("check5",false));
        chk_12.setChecked(pref.getBoolean("check6",false));

        return view;

    }
    public void onDestroy() {
        super.onDestroy();
        Context context = getActivity();
        SharedPreferences pref = context.getSharedPreferences("pref", 0);

        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean("check",chk_6.isChecked());
        editor.putBoolean("check1",chk_7.isChecked());
        editor.putBoolean("check2",chk_8.isChecked());
        editor.putBoolean("check3",chk_9.isChecked());
        editor.putBoolean("check4",chk_10.isChecked());
        editor.putBoolean("check5",chk_11.isChecked());
        editor.putBoolean("check6",chk_12.isChecked());
        editor.commit(); // 세이브를 완료해라.
    }
}