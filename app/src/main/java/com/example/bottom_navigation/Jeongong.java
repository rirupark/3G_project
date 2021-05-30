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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;

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
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    public String classname;
    public String area;
    public String credit;
    public String token;
    public String grade;
    public String gradenum;
    DataTemp dataTemp = DataTemp.getInstance();

    public static Jeongong newinstance() {
        return new Jeongong();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_jeongong, null);

        ImageButton btn_back = (ImageButton) view.findViewById(R.id.btn_back2);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Checklist.newinstance());
            }
        });
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mDatabase = database.getReference("UserInfo");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        recyclerView = view.findViewById(R.id.re_jeongong2);//아이디 연결
        recyclerView.setHasFixedSize(true);//리사이클러뷰 기존성능강화
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();//User 객체를 담을 어레이 리스트(어댑터쪽으로)
        //Log.e("userDB", databaseReference.toString());

        Spinner spn_jeongong = (Spinner) view.findViewById(R.id.spn_jeongong);
        //spn_jeongong.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) view.getContext());
        Spinner spn_grade = (Spinner) view.findViewById(R.id.spn_grade);
        //spn_grade.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) view.getContext());
        if (user != null) {
            // Name, email address, and profile photo Url
            mDatabase.orderByChild("idToken").equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    UserAccount userAccount = snapshot.getValue(UserAccount.class);

                    assert userAccount != null;
                    grade = userAccount.getStd_grade_num();
                    if (grade.equals("18학번")) {
                        databaseReference = database.getReference("User18");

                    } else if (grade.equals("19학번")) {

                        databaseReference = database.getReference("User19");

                    } else if (grade.equals("20학번")) {

                        databaseReference = database.getReference("User20");

                    } else if (grade.equals("21학번")) {

                        databaseReference = database.getReference("User");
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

            //UserAccount userAccount = new UserAccount();
            spn_jeongong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    arrayList.clear();
                    adapter = new CustomAdapter(arrayList, getActivity());
                    recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
                    if (position == 0) {
                        databaseReference.orderByChild("area_grade").equalTo("m_necessary_1").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                User user = snapshot.getValue(User.class); // 만들어둔 User 객체에 데이터를 담는다.
                                arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비
                                Log.d("159753", "onChildAdded: "+dataTemp.getTempString());

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
                                if (position == 0) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_necessary_1").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                            User user = snapshot.getValue(User.class); // 만들어둔 User 객체에 데이터를 담는다.
                                            arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비

                                            Log.d("159753", "onChildAdded: "+dataTemp.getTempString());
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
                                } else if (position == 1) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_necessary_2").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                            User user = snapshot.getValue(User.class); // 만들어둔 User 객체에 데이터를 담는다.
                                            arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비

                                            Log.d("159753", "onChildAdded: "+dataTemp.getTempString());
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
                                } else if (position == 2) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_necessary_3").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                            User user = snapshot.getValue(User.class); // 만들어둔 User 객체에 데이터를 담는다.
                                            arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비

                                            Log.d("159753", "onChildAdded: "+dataTemp.getTempString());
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
                                } else if (position == 3) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_necessary_4").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                            User user = snapshot.getValue(User.class); // 만들어둔 User 객체에 데이터를 담는다.
                                            arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비
                                            Log.d("159753", "onChildAdded: "+dataTemp.getTempString());

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

                    } else if (position == 1) {

                        spn_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override

                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                arrayList.clear();
                                adapter = new CustomAdapter(arrayList, getActivity());
                                recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터연결
                                if (position == 0) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_select_1").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                                            User user = snapshot.getValue(User.class); // 만들어둔 User 객체에 데이터를 담는다.
                                            arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비

                                            Log.d("159753", "onChildAdded: "+dataTemp.getTempString());
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
                                } else if (position == 1) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_select_2").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                            User user = snapshot.getValue(User.class); // 만들어둔 User 객체에 데이터를 담는다.
                                            arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비

                                            Log.d("159753", "onChildAdded: "+dataTemp.getTempString());
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
                                } else if (position == 2) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_select_3").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                            User user = snapshot.getValue(User.class); // 만들어둔 User 객체에 데이터를 담는다.
                                            arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비
                                            Log.d("159753", "onChildAdded: "+dataTemp.getTempString());

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
                                } else if (position == 3) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_select_4").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                            User user = snapshot.getValue(User.class); // 만들어둔 User 객체에 데이터를 담는다.
                                            arrayList.add(user); //담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼준비

                                            Log.d("159753", "onChildAdded: "+dataTemp.getTempString());
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
            Log.d("159753", "onChildAdded: "+dataTemp.getTempString());





//----------------------------------------------------선택, 필수------------------------------------------------------------
            linearLayoutManager = new VariableScrollSpeedLinearLayoutManager(getActivity(), 100); // 스크롤 속도 조절
            return view;
        }


        return null;
    }
}












