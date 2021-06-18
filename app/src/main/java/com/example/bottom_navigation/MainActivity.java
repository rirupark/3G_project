package com.example.bottom_navigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    public FragmentManager manager;
    public FragmentTransaction transaction;
    public Calculator calculator;
    public Checklist checklist;
    public Ppt ppt;
    public Mypage mypage;

    Checklist fragment_checklist;
    Jeongong fragment_jeongong;
    Mypage fragment_mypage;

    private TextView user_name;
    private ImageView user_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_frame,Jeongong.newinstance()).commit();
        fragment_checklist = new Checklist();
        fragment_jeongong = new Jeongong();



        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
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

        setFrag(0);
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.main_frame,fragment).commit();
    }



    private void setFrag(int n){
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        switch (n){
            case 0:
                transaction.replace(R.id.main_frame, calculator);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.main_frame, checklist);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 2:
                transaction.replace(R.id.main_frame, ppt);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 3:
                transaction.replace(R.id.main_frame, mypage);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();

    }
}

