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
    CheckBox chk_1;
    CheckBox chk_2;
    CheckBox chk_3;
    CheckBox chk_4;
    CheckBox chk_5;
    CheckBox chk_6;
    CheckBox chk_7;

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
        chk_1= (CheckBox)view.findViewById(R.id.chk_1);
        chk_2= (CheckBox)view.findViewById(R.id.chk_2);
        chk_3= (CheckBox)view.findViewById(R.id.chk_3);
        chk_4= (CheckBox)view.findViewById(R.id.chk_4);
        chk_5= (CheckBox)view.findViewById(R.id.chk_5);
        chk_6= (CheckBox)view.findViewById(R.id.chk_6);
        chk_7= (CheckBox)view.findViewById(R.id.chk_7);

        chk_1.setChecked(pref.getBoolean("check",false));
        chk_2.setChecked(pref.getBoolean("check1",false));
        chk_3.setChecked(pref.getBoolean("check2",false));
        chk_4.setChecked(pref.getBoolean("check3",false));
        chk_5.setChecked(pref.getBoolean("check4",false));
        chk_6.setChecked(pref.getBoolean("check5",false));
        chk_7.setChecked(pref.getBoolean("check6",false));


        return view;

    }
    public void onDestroy() {
        super.onDestroy();
        Context context = getActivity();
        SharedPreferences pref = context.getSharedPreferences("pref", 0);

        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean("check",chk_1.isChecked());
        editor.putBoolean("check1",chk_2.isChecked());
        editor.putBoolean("check2",chk_3.isChecked());
        editor.putBoolean("check3",chk_4.isChecked());
        editor.putBoolean("check4",chk_5.isChecked());
        editor.putBoolean("check5",chk_6.isChecked());
        editor.putBoolean("check6",chk_7.isChecked());
        editor.commit(); // 세이브를 완료해라.
    }
}