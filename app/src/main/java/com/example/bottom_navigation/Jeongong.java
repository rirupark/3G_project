package com.example.bottom_navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Jeongong extends Fragment {

    private View view;
    private Spinner spn_jeongong, spn_grade;
    private TextView tv_result3, tv_result4;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private LinearLayoutManager linearLayoutManager;





    public static Jeongong newinstance() {
        return new Jeongong();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jeongong, null);

        ImageButton btn_back = (ImageButton)view.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Checklist.newinstance());
            }
        });

        recyclerView = view.findViewById(R.id.re_jeongong2);//아이디 연결
        recyclerView.setHasFixedSize(true);//리사이클러뷰 기존성능강화
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();//User 객체를 담을 어레이 리스트(어댑터쪽으로)

        Spinner spn_jeongong = (Spinner)view.findViewById(R.id.spn_jeongong);
        //spn_jeongong.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) view.getContext());
        Spinner spn_grade = (Spinner)view.findViewById(R.id.spn_grade);
        //spn_grade.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) view.getContext());



        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("User"); // DB테이블 연결
        Log.e("userDB", databaseReference.toString());


        /*
        spn_jeongong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    spn_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position == 0){
                                databaseReference.orderByChild("area_grade").equalTo("m_necessary_1").addChildEventListener(new ChildEventListener() {
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
                            }
                            else if(position == 1){
                                databaseReference.orderByChild("area_grade").equalTo("m_necessary_2").addChildEventListener(new ChildEventListener() {
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
                            }
                            else if(position == 2){
                                databaseReference.orderByChild("area_grade").equalTo("m_necessary_3").addChildEventListener(new ChildEventListener() {
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
                            }
                            else if(position == 3){
                                databaseReference.orderByChild("area_grade").equalTo("m_necessary_4").addChildEventListener(new ChildEventListener() {
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
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                else if(position == 1){
                    spn_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position == 0){
                                databaseReference.orderByChild("area_grade").equalTo("m_select_1").addChildEventListener(new ChildEventListener() {
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
                            }
                            else if(position == 1){
                                databaseReference.orderByChild("area_grade").equalTo("m_necessary_2").addChildEventListener(new ChildEventListener() {
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
                            }
                            else if(position == 2){
                                databaseReference.orderByChild("area_grade").equalTo("m_necessary_3").addChildEventListener(new ChildEventListener() {
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
                            }
                            else if(position == 3){
                                databaseReference.orderByChild("area_grade").equalTo("m_necessary_4").addChildEventListener(new ChildEventListener() {
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
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
*/ //이중스피너 시도



/*
        spn_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    databaseReference.orderByChild("grade").equalTo(1).addChildEventListener(new ChildEventListener() {
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
                    adapter = new CustomAdapter(arrayList, getActivity());
                    recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
                }
                else if (position == 1){
                    databaseReference.orderByChild("grade").equalTo(2).addChildEventListener(new ChildEventListener() {
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
                    adapter = new CustomAdapter(arrayList, getActivity());
                    recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
                }

                else if(position == 2){
                    databaseReference.orderByChild("grade").equalTo(3).addChildEventListener(new ChildEventListener() {
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
                    adapter = new CustomAdapter(arrayList, getActivity());
                    recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
                }
                else if(position == 3){
                    databaseReference.orderByChild("grade").equalTo(4).addChildEventListener(new ChildEventListener() {
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
                    adapter = new CustomAdapter(arrayList, getActivity());
                    recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
                }
            }




            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        */
//----------------------------------------------------선택, 필수------------------------------------------------------------

        spn_jeongong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                arrayList.clear();
                adapter = new CustomAdapter(arrayList, getActivity());
                recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
                if(position == 0){
                    databaseReference.orderByChild("area_grade").equalTo("m_necessary_1").addChildEventListener(new ChildEventListener() {
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
                    spn_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override

                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            arrayList.clear();
                            adapter = new CustomAdapter(arrayList, getActivity());
                            recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
                            if(position == 0){
                                databaseReference.orderByChild("area_grade").equalTo("m_necessary_1").addChildEventListener(new ChildEventListener() {
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
                            }
                            else if(position == 1){
                                databaseReference.orderByChild("area_grade").equalTo("m_necessary_2").addChildEventListener(new ChildEventListener() {
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
                            }
                            else if(position == 2){
                                databaseReference.orderByChild("area_grade").equalTo("m_necessary_3").addChildEventListener(new ChildEventListener() {
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
                            }
                            else if(position == 3){
                                databaseReference.orderByChild("area_grade").equalTo("m_necessary_4").addChildEventListener(new ChildEventListener() {
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
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    arrayList.clear();
                    adapter = new CustomAdapter(arrayList, getActivity());
                    recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
                }

                else if(position == 1){

                    spn_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override

                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            arrayList.clear();
                            adapter = new CustomAdapter(arrayList, getActivity());
                            recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
                            if(position == 0){
                                databaseReference.orderByChild("area_grade").equalTo("m_select_1").addChildEventListener(new ChildEventListener() {
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
                            }
                            else if(position == 1){
                                databaseReference.orderByChild("area_grade").equalTo("m_select_2").addChildEventListener(new ChildEventListener() {
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
                            }
                            else if(position == 2){
                                databaseReference.orderByChild("area_grade").equalTo("m_select_3").addChildEventListener(new ChildEventListener() {
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
                            }
                            else if(position == 3){
                                databaseReference.orderByChild("area_grade").equalTo("m_select_4").addChildEventListener(new ChildEventListener() {
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
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    arrayList.clear();
                    adapter = new CustomAdapter(arrayList, getActivity());
                    recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        /*--------- sharedPreference (체크박스 유지) -------------*/

        /*databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트가 존재하지 않게 초기화(방지차원)
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class); // 만들어둔 User 객체에 데이터를 담는다.
                    arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비


                }
                adapter.notifyDataSetChanged();  // 리스트 저장 및 새로고침
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //디비를 가져오던중 에러발생시


            }
        }); */ //??



        linearLayoutManager = new VariableScrollSpeedLinearLayoutManager(getActivity(), 100); // 스크롤 속도 조절

        return view;
    }




}

        /*
        spn_jeongong = (Spinner)view.findViewById(R.id.spn_jeongong);
        spn_grade = (Spinner)view.findViewById(R.id.spn_grade);
        tv_result3 = (TextView)view.findViewById(R.id.tv_result3);
        tv_result4 = (TextView)view.findViewById(R.id.tv_result4);


        spn_jeongong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_result3.setText(parent.getItemAtPosition(position).toString());
                ((TextView)parent.getChildAt(0)).setTextColor(Color.WHITE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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










