package com.example.bottom_navigation;

import android.os.Bundle;

//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Calculator extends Fragment {
    public static Calculator newinstance() {    //////모든 프레그먼트에 newinstance메소드가 있어야함..!!
        return new Calculator();
    }

    public Calculator() {
    }

    private View view;
    private RecyclerView recyclerView;
    private ClassnameAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;
    private ArrayList<String> arrayList1;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseAuth mAuth;
    public int sum=0;
    public static String[] className = new String[20];
    public boolean flag = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calculator, null);
        adapter = new ClassnameAdapter();

        recyclerView = view.findViewById(R.id.re_jeongong);//아이디 연결
        recyclerView.setHasFixedSize(true);//리사이클러뷰 기존성능강화
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        arrayList1 = new ArrayList<>();//User 객체를 담을 어레이 리스트(어댑터쪽으로)

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("UserInfo"); // DB테이블 연결
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        getSumData();




        return view;

    }
    void getRecyclerViewData( String classname, ClassnameAdapter adapter){

        adapter.addItem(classname);
        adapter.notifyDataSetChanged();
    } //리사이클러뷰 연결 어댑터
    void getSumData(){

        databaseReference.child(mAuth.getUid()).child("finish").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(Task<DataSnapshot> task) {
                Log.d("159753", "onComplete: " + task.getResult().getValue());

                if (task.getResult().getValue() != null) {
                    String t = String.valueOf(task.getResult().getValue());


                    sum = 0;
                    String[] c = new String[30];
                    String a[] = t.split("\\}");
                    for (int i = 0; i < a.length; i++) {
                        Log.d("741852", "onComplete: " + a[i]);
                        String[] l = new String[30];
                        l = a[i].split("credit=");
                        c[i] = l[1];
                        sum += Integer.parseInt(c[i]);
                        Log.d("156321", "onComplete: " + sum);

                    }
                    TextView tv_calview = (TextView) view.findViewById(R.id.tv_calview);
                    tv_calview.setText("총 이수한 학점은 " + sum + "학점 입니다.");
                    TextView tv_progress = (TextView) view.findViewById(R.id.tv_progress);
                    tv_progress.setText("현재 " + (sum*100)/130 + "% 이수했습니다.");
                    ProgressBar bar_jeon = (ProgressBar) view.findViewById(R.id.bar_jeon);
                    bar_jeon.setProgress(sum);
                }
                else{
                    TextView tv_calview = (TextView) view.findViewById(R.id.tv_calview);
                    tv_calview.setText("총 이수한 학점은 " + 0+ "학점 입니다.");
                    TextView tv_progress = (TextView) view.findViewById(R.id.tv_progress);
                    tv_progress.setText("현재 " + 0 + "% 이수했습니다.");
                    ProgressBar bar_jeon = (ProgressBar) view.findViewById(R.id.bar_jeon);
                    bar_jeon.setProgress(0);
                }
            }
        });
    }
    void getClassNameData(){
        databaseReference.child(mAuth.getUid()).child("finish").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(Task<DataSnapshot> task) {
                recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
                String a = String.valueOf(task.getResult().getValue());
                String[] d = a.split("className=");
                for (int i = 1; i < d.length; i++) {
                    String[] e = d[i].split(",");
                    className[i] = e[0];
                    getRecyclerViewData(className[i], adapter);
                }
                //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비
                // 리스트 저장 및 새로고침
            }
        });
    } // userInfo에 저장된 className 값 불러오는 함수




    @Override
    public void onResume() {
        super.onResume();
        getClassNameData();
    }
}