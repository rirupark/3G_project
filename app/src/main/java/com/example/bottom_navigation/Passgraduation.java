package com.example.bottom_navigation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Passgraduation extends Fragment {

    public static Passgraduation newinstance() {
        return new Passgraduation();
    }

    public Passgraduation() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passgraduation, null);

        ImageButton btn_back = (ImageButton) view.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(Checklist.newinstance());
            }
        });

        ImageButton btn_ess = (ImageButton) view.findViewById(R.id.btn_ess_passgra);
        btn_ess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(PassgraEss.newinstance());
            }
        });

        ImageButton btn_selec = (ImageButton) view.findViewById(R.id.btn_selec_passgra);
        btn_selec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragment(PassgraSelec.newinstance());
            }
        });
        return view;
    }
    
}



