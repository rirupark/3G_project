package com.example.bottom_navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Gyoyang extends Fragment {
    private  View view;
    private Spinner spn_gyoyang,spn_areagyo;
    private TextView tv_result3,tv_result4;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<UserData> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public static Gyoyang newinstance(){
    return new Gyoyang();
}
public Gyoyang(){

}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view =inflater.inflate(R.layout.fragment_gyoyang,container,false);

        //리사이클러뷰.
        recyclerView = view.findViewById(R.id.re_jeongong2);//아이디 연결
        recyclerView.setHasFixedSize(true);//리사이클러뷰 기존성능강화
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();//User 객체를 담을 어레이 리스트(어댑터쪽으로)

        //User user= new User(email, name);
        //user.setName("nugulhie@gmail.com");
        //user.setEmail("nuuglhie");
        //ReadAndWriteSnippets dbhelper = new ReadAndWriteSnippets();
        //dbhelper.writeNewUser(user);

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("User"); // DB테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트가 존재하지 않게 초기화(방지차원)
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    UserData user = snapshot.getValue(UserData.class); // 만들어둔 User 객체에 데이터를 담는다.
                    arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비
                }
                adapter.notifyDataSetChanged();  // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //디비를 가져오던중 에러발생시

            }
        });
        adapter = new CustomAdapter(arrayList, getActivity());
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결



        /*
        spn_gyoyang = (Spinner)view.findViewById(R.id.spn_gyoyang);
        spn_areagyo = (Spinner)view.findViewById(R.id.spn_areagyo);

        spn_gyoyang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_result3.setText(parent.getItemAtPosition(position).toString());
                ((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn_areagyo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_result4.setText(parent.getItemAtPosition(position).toString());
                ((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

         */


        return view;

    }
}
