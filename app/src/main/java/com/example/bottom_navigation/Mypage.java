package com.example.bottom_navigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.okhttp.internal.DiskLruCache;


public class Mypage extends Fragment {

    private  String result1,result2;
    private  TextView nametext;
    private  TextView mailtext;
    private  TextView gradetext;
    private String username;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private  String grade;


    public static Mypage newinstance(){    //////모든 프레그먼트에 newinstance메소드가 있어야함..!!
        return new Mypage();
    }

    public Mypage(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mypage,null);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = database.getReference("UserInfo");


        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            String grade = getActivity().getIntent().getStringExtra("grade");
            final String[] grd = new String[1];



            nametext = view.findViewById(R.id.nametext);
            nametext.setText(name);

            mailtext = view.findViewById(R.id.mailtext);
            mailtext.setText(email);

            gradetext = view.findViewById(R.id.gradetext);




            mDatabase.orderByChild("idToken").equalTo(user.getUid()).limitToFirst(1).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    UserAccount userAccount = snapshot.getValue(UserAccount.class);
                    grd[0] = userAccount.getStd_grade_num();


                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            gradetext.setText(grd[0]);
            // ---------------------- 사용자테이블에서 학번 데이터 출력하기 수정중 ---------------------------
//            gradetext = view.findViewById(R.id.gradetext);
//            mDatabase.child("UserInfo").child("std_grade_num").equalTo("19학번").addChildEventListener(new ChildEventListener() {
//                @Override
//                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                    UserAccount userAccount = snapshot.getValue(UserAccount.class);
//                    Log.e("학번","사용자의 학번 : " + userAccount.getStd_grade_num());
//                    grade = userAccount.getStd_grade_num();
//
//                }
//
//                @Override
//                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                }
//
//                @Override
//                public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//                }
//
//                @Override
//                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//
//            gradetext.setText(grade);
//
//
        }


        /* ------- 회원탈퇴 -----------------------------------------------*/
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        Button btn_quit = (Button)view.findViewById(R.id.btn_quit);
        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_quit:
                        // ----- Firebase Auth에서 계정 삭제. -----
                        AuthUI.getInstance()
                                .delete(getActivity())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    public void onComplete(@NonNull Task<Void> task) {
                                        getActivity().finish();
                                        startActivity(new Intent(getActivity(), Login.class));
                                        Toast.makeText(getActivity(), "회원탈퇴 되었습니다.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });

                        // ----- Firebase Realtime에서 테이블 삭제. -----
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        DatabaseReference hopperRef = mDatabase.child("UserInfo").child(firebaseUser.getUid());
                        hopperRef.removeValue();

                        Log.e("회원탈퇴","버튼입력");

                        break;
                }
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





/* ---------------- ResultActivity에서 받아온 데이터----------------------


=======
/* ---------------- ResultActivity에서 받아온 데이터----------------------*/


        /*
        nametext = view.findViewById(R.id.nametext);
        mailtext = view.findViewById(R.id.mailtext);



        if(getArguments() != null) {

             result1 = getArguments().getString("user_name");
             nametext.setText(result1);

            result2 = getArguments().getString("user_mail");
            mailtext.setText(result2);


        }

         */



        return view;




    }


}

