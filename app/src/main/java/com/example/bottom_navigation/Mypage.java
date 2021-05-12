package com.example.bottom_navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Mypage extends Fragment {

    ResultActivity resultActivity;
    private  String result1,result2;
    private  TextView nametext;
    private  TextView mailtext;
    private String username;

    public static Mypage newinstance(){    //////모든 프레그먼트에 newinstance메소드가 있어야함..!!
        return new Mypage();
    }

    public Mypage(){
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mypage,null);

        nametext = view.findViewById(R.id.nametext);
        username = resultActivity.findViewById(R.id.user_name2).toString();
        nametext.setText(username);

        Button btn_change = (Button)view.findViewById(R.id.btn_change);
        btn_change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).replaceFragment(ChangeMy.newinstance());
            }
        });

        /* -------- 로그아웃 버튼 클릭 시 다시 로그인 화면을 돌아가서 계정 선택 ---------*/
        Button btn_logout = (Button)view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_logout:
                        //user_logout();
                        AuthUI.getInstance()
                                .signOut(getActivity())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    public void onComplete(@NonNull Task<Void> task) {
                                        getActivity().finish();
                                        startActivity(new Intent(getActivity(), Login.class));
                                        Toast.makeText(getActivity(), "로그아웃 되었습니다.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                        Log.e("로그아웃","버튼입력");
                        break;
                }
            }
        });




        return view;




    }


}

