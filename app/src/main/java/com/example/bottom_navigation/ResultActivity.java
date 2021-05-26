package com.example.bottom_navigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.okhttp.internal.DiskLruCache;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.RegEx;

public class ResultActivity extends AppCompatActivity {

    private TextView user_name2;
    private ImageView user_img2;
    private TextView user_mail2;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth; // 파베 인증 객체

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //상단바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        String photoUrl = intent.getStringExtra("photoUrl");
        String email = intent.getStringExtra("email");

        user_name2 = findViewById(R.id.user_name2);
        user_name2.setText(nickName);

        user_mail2 = findViewById(R.id.user_mail2);
        user_mail2.setText(email);

        user_img2 = findViewById(R.id.user_img2);
        Glide.with(this).load(photoUrl).into(user_img2);



        Button imageButton = (Button) findViewById(R.id.btn_start_3g);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        UserAccount account1 = new UserAccount();
        auth = FirebaseAuth.getInstance(); // 파이어베이스 인증 객체 초기화.
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser firebaseUser = auth.getCurrentUser();

        /* -----학번 스피너에서 항목 선택 시 데이터 베이스 사용자 테이블에 학번 필드 생성 및 저장 ----------*/
        Spinner spn_grade_choose = (Spinner)findViewById(R.id.spn_grade_choose);
        spn_grade_choose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        updateGradeNum("18학번");
                        break;
                    case 1:
                        updateGradeNum("19학번");
                        break;
                    case 2:
                        updateGradeNum("20학번");
                        break;
                    case 3:
                        updateGradeNum("21학번");
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ResultActivity.this, "학번을 선택해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

    }




    //값을 파이어베이스 Realtime database에 업데이트하는 함수
//    public void updateGradeNum(String fieldName, String value) {
//        FirebaseUser firebaseUser = auth.getCurrentUser();
//        DatabaseReference hopperRef = mDatabase.child("UserInfo").child(firebaseUser.getUid());
//        Map<String, Object> hopperUpdates = new HashMap<>(); // 기존 사용자 테이블에 학번 데이터 추가.
//        hopperUpdates.put(fieldName, value);
//        hopperRef.updateChildren(hopperUpdates);
//    }


    //값을 파이어베이스 Realtime database의 std_grade_num 필드에 업데이트하는 함수
        public void updateGradeNum(String value) {
        FirebaseUser firebaseUser = auth.getCurrentUser();
        DatabaseReference hopperRef = mDatabase.child("UserInfo").child(firebaseUser.getUid());
        Map<String, Object> hopperUpdates = new HashMap<>(); // 기존 사용자 테이블에 학번 데이터 추가.
        hopperUpdates.put("std_grade_num", value);
        hopperRef.updateChildren(hopperUpdates);



    }
}




