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
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Mypage extends Fragment {

    ViewGroup viewGroup;
    TextView user_name; // 사용자 닉네
    ImageView user_img; // 이미지 뷰

    public static Mypage newinstance(){    //////모든 프레그먼트에 newinstance메소드가 있어야함..!!
        return new Mypage();
    }

    public Mypage(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mypage,null);
        Button btn_change = (Button)view.findViewById(R.id.btn_change);
        btn_change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).replaceFragment(ChangeMy.newinstance());

            }

        });
        return view;


        // login엑티비티에서 여기로 구글 로그인 해서 가져온 account데이터를 받아오는 코드 추가해야 해요. 로그아웃도 추가해야합니다 ㅎㅎㅎ

    }


}

