package com.example.bottom_navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView; // 바텀네비게이션 뷰
    public FragmentManager manager;
    public FragmentTransaction transaction;
    public Calculator calculator;
    public Checklist checklist;
    public Ppt ppt;
    public Mypage mypage;

    Checklist fragment_checklist;
    Jeongong fragment_jeongong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_frame,Jeongong.newinstance()).commit();
        fragment_checklist = new Checklist();
        fragment_jeongong = new Jeongong();



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

    public void replaceFragment(Fragment fragment){      ////// 화면전환 메소드 프레그먼트는 이메소드를 받아서 화면전환!!!!
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment).commit();
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

