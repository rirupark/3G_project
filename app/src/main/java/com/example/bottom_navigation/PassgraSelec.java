package com.example.bottom_navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class PassgraSelec extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private PassAdapter passAdapter;
    private ArrayList<Pass> arrayList;
    public static Fragment newinstance() {
        return new PassgraSelec();
    }

    public PassgraSelec(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passgra_selec,null);

        recyclerView = view.findViewById(R.id.re_Pass);//아이디 연결
        recyclerView.setHasFixedSize(true);//리사이클러뷰 기존성능강화
        linearLayoutManager= new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        //User 객체를 담을 어레이 리스트(어댑터쪽으로)


        passAdapter = new PassAdapter(arrayList);
        recyclerView.setAdapter(passAdapter);




        ImageButton btn_back2 = (ImageButton)view.findViewById(R.id.btn_back2);
        btn_back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Passgraduation.newinstance());
            }
        });





        return view;

    }
}
