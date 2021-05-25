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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.stream.IntStream;


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
    private LinearLayoutManager linearLayoutManager;
    int sum;




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

/*------------------ 계산 ----------------------------- 가공된 데이터값 넣을 예정*/

        databaseReference.orderByChild("credit").startAt(1).endAt(40).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                User user = snapshot.getValue(User.class); // 만들어둔 User 객체에 데이터를 담는다.
                arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비
                for (int i = 0; i < arrayList.size(); i++) {
                    sum +=  Integer.parseInt(String.valueOf(arrayList.get(i).getCredit()));
                }
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




        int jeon_sum = 60;
        int gyo_sum = 30;

        /*------------------ 이수학점계산텍스트출력 ----------------------------- */
        // sum을 가공된 데이터값으로 바꾸기

        TextView tv_calview = (TextView)view.findViewById(R.id.tv_calview);
        tv_calview.setText("총이수한 학점은 "+sum+"점 입니다.");

        /*------------------ 프로그레스바 퍼센테이지 바 ----------------------------- */
        // jeon_sum 과 gyo_sum을 데이터값으로 바꾸기

        ProgressBar bar_jeon = (ProgressBar)view.findViewById(R.id.bar_jeon);
        bar_jeon.setProgress(jeon_sum);
        ProgressBar bar_gyo = (ProgressBar)view.findViewById(R.id.bar_gyo);
        bar_gyo.setProgress(gyo_sum);



        Button button7 = (Button)view.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                databaseReference.orderByChild("id").startAt(1).endAt(40).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        User user = snapshot.getValue(User.class); // 만들어둔 User 객체에 데이터를 담는다.
                        arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비
                        adapter.notifyDataSetChanged();  // 리스트 저장 및 새로고침
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

                arrayList.clear();
                adapter = new CalAdapter(arrayList, getActivity());
                recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
            }
        });

        linearLayoutManager = new VariableScrollSpeedLinearLayoutManager(getActivity(), 4); // 스크롤 속도 조절


        Button button9 = (Button)view.findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                databaseReference.orderByChild("id").startAt(41).endAt(69).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        User user = snapshot.getValue(User.class); // 만들어둔 User 객체에 데이터를 담는다.
                        arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비
                        adapter.notifyDataSetChanged();  // 리스트 저장 및 새로고침
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
                arrayList.clear();
                adapter = new CalAdapter(arrayList, getActivity());
                recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
            }
        });

        return view;
    }
}


