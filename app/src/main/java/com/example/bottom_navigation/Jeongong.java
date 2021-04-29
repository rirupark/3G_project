package com.example.bottom_navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Jeongong extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView; // 바텀네비게이션 뷰
    public FragmentManager manager;
    public FragmentTransaction transaction;
    public Calculator calculator;
    public Checklist checklist;
    public Ppt ppt;
    public Mypage mypage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.calculator:
                        setFrag(0);
                        break;
                    case R.id.checklist:
                        setFrag(1);
                        break;
                    case R.id.ppt:
                        setFrag(2);
                        break;
                    case R.id.mypage:
                        setFrag(3);
                        break;

                }
                return true;
            }
        });

        calculator = new Calculator();
        checklist = new Checklist();
        ppt = new Ppt();
        mypage = new Mypage();

        setFrag(0); // 첫화면 설정
    }

    // 프래그먼트 교체가 일어나는 메서드
    private void setFrag(int n){

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();


        switch (n){
            case 0:
                transaction.replace(R.id.main_frame, calculator);
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.main_frame, checklist);
                transaction.commit();
                break;
            case 2:
                transaction.replace(R.id.main_frame, ppt);
                transaction.commit();
                break;
            case 3:
                transaction.replace(R.id.main_frame, mypage);
                transaction.commit();
                break;
        }
    }

}

