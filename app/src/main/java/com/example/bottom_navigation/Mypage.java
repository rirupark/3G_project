package com.example.bottom_navigation;

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

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Mypage extends Fragment {

    private  String result1,result2;
    private  TextView nametext;
    private  TextView mailtext;
    private String username;
    private DatabaseReference mDatabase;

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
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();


            nametext = view.findViewById(R.id.nametext);
            nametext.setText(name);

            mailtext = view.findViewById(R.id.mailtext);
            mailtext.setText(email);


        }

        /*mDatabase = FirebaseDatabase.getInstance().getReference("UserInfo");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot snapshot;
                dataSnapshot.getChildren();
                username = dataSnapshot.child("").child("name").getValue().toString();
                nametext = view.findViewById(R.id.nametext);
                nametext.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */




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

