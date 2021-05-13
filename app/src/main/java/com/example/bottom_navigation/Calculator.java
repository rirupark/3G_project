package com.example.bottom_navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Calculator extends Fragment {
    public static Calculator newinstance(){    //////모든 프레그먼트에 newinstance메소드가 있어야함..!!
        return new Calculator();
    }

    public Calculator(){
    }

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private String O_jeonname;
    private TextView jeonname;
    private LinearLayoutManager linearLayoutManager;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calculator, null);



        recyclerView = view.findViewById(R.id.re_jeongong);//아이디 연결
        recyclerView.setHasFixedSize(true);//리사이클러뷰 기존성능강화
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();//User 객체를 담을 어레이 리스트(어댑터쪽으로)

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("User"); // DB테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트가 존재하지 않게 초기화(방지차원)
                //Intent intent = new Intent(getActivity(),MainActivity.class);
               // startActivity(intent);
               // String jeon = intent.getStringExtra("User");
                //O_jeonname = dataSnapshot.child(jeon).child("area").getValue().toString();
               // Log.v("m_",O_jeonname);
               // jeonname.setText(O_jeonname);

            for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class); // 만들어둔 User 객체에 데이터를 담는다.
                    arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비
                }
                }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {
          //디비를 가져오던중 에러발생시
                //adapter.notifyDataSetChanged();  // 리스트 저장 및 새로고침

            }
        });

        Button button7 = (Button)view.findViewById(R.id.button7);
       button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                adapter = new CalAdapter(arrayList, getActivity());
                recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
            }
        });

        linearLayoutManager = new VariableScrollSpeedLinearLayoutManager(getActivity(), 4); // 스크롤 속도 조절


        Button button9 = (Button)view.findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                adapter = new CalAdapter(arrayList, getActivity());
                recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
            }
        });

        
        return view;
    }
}


