package com.example.bottom_navigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_mypage,null);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = database.getReference("UserInfo");


        if (user != null) {

            mDatabase.orderByChild("idToken").equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot snapshot, @Nullable String previousChildName) {
                    UserAccount userAccount = snapshot.getValue(UserAccount.class);
                    if(userAccount.getStd_grade_num() != null){
                        loadText(user, view, userAccount);
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });

        }


        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        Button btn_quit = (Button)view.findViewById(R.id.btn_quit);
        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_quit:

                        AuthUI.getInstance()
                                .delete(getActivity())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    public void onComplete(Task<Void> task) {
                                        getActivity().finish();
                                        startActivity(new Intent(getActivity(), Login.class));
                                        Toast.makeText(getActivity(), "회원탈퇴 되었습니다.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });


                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        DatabaseReference hopperRef = mDatabase.child("UserInfo").child(firebaseUser.getUid());
                        hopperRef.removeValue();

                        Log.e("회원탈퇴","버튼입력");

                        break;
                }
            }
        });



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
                                    public void onComplete(Task<Void> task) {
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
    void loadText(FirebaseUser user, View view, UserAccount userAccount){
        String name = user.getDisplayName();
        String email = user.getEmail();
        String grade = getActivity().getIntent().getStringExtra("grade");

        nametext = view.findViewById(R.id.nametext);
        nametext.setText(name);

        mailtext = view.findViewById(R.id.mailtext);
        mailtext.setText(email);

        gradetext = view.findViewById(R.id.gradetext);
        gradetext.setText(userAccount.getStd_grade_num());
    }
}

