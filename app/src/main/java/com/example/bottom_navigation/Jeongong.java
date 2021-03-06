package com.example.bottom_navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

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

import java.util.ArrayList;

public class Jeongong extends Fragment {

    private View view;
    private Spinner spn_jeongong, spn_grade;
    private TextView tv_result3, tv_result4;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private CustomAdapter customAdapter;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

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


        recyclerView = view.findViewById(R.id.re_jeongong2);//????????? ??????
        recyclerView.setHasFixedSize(true);//?????????????????? ??????????????????
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();//User ????????? ?????? ????????? ?????????(??????????????????)
        //Log.e("userDB", databaseReference.toString());

        adapter = new CustomAdapter(arrayList, getActivity());
        recyclerView.setAdapter(adapter); //????????????????????? ???????????????


        Spinner spn_jeongong = (Spinner) view.findViewById(R.id.spn_jeongong);

        Spinner spn_grade = (Spinner) view.findViewById(R.id.spn_grade);

        if (user != null) {
            // Name, email address, and profile photo Url
            mDatabase.orderByChild("idToken").equalTo(user.getUid()).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot snapshot, @Nullable String previousChildName) {
                    UserAccount userAccount = snapshot.getValue(UserAccount.class);

                    assert userAccount != null;
                    grade = userAccount.getStd_grade_num();
                    if (grade.equals("18??????")) {

                        databaseReference = database.getReference("User18");


                    } else if (grade.equals("19??????")) {

                        databaseReference = database.getReference("User19");


                    } else if (grade.equals("20??????")) {

                        databaseReference = database.getReference("User20");


                    } else if (grade.equals("21??????")) {

                        databaseReference = database.getReference("User");

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


            spn_jeongong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    arrayList.clear();
                    if (position == 0) {
                        databaseReference.orderByChild("area_grade").equalTo("m_necessary_1").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot snapshot, @Nullable String previousChildName) {
                                User user = snapshot.getValue(User.class); // ???????????? User ????????? ???????????? ?????????.
                                arrayList.add(user); //?????? ??????????????? ?????????????????? ?????? ????????????????????? ????????????

                                adapter.notifyDataSetChanged();  // ????????? ?????? ??? ????????????
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

                        spn_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override

                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                arrayList.clear();
                                if (position == 0) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_necessary_1").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot snapshot, @Nullable String previousChildName) {

                                            User user = snapshot.getValue(User.class); // ???????????? User ????????? ???????????? ?????????.
                                            arrayList.add(user); //?????? ??????????????? ?????????????????? ?????? ????????????????????? ????????????

                                            adapter.notifyDataSetChanged();  // ????????? ?????? ??? ????????????
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
                                } else if (position == 1) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_necessary_2").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot snapshot, @Nullable String previousChildName) {
                                            User user = snapshot.getValue(User.class); // ???????????? User ????????? ???????????? ?????????.
                                            arrayList.add(user); //?????? ??????????????? ?????????????????? ?????? ????????????????????? ????????????

                                            adapter.notifyDataSetChanged();  // ????????? ?????? ??? ????????????
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
                                } else if (position == 2) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_necessary_3").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot snapshot, @Nullable String previousChildName) {
                                            User user = snapshot.getValue(User.class); // ???????????? User ????????? ???????????? ?????????.
                                            arrayList.add(user); //?????? ??????????????? ?????????????????? ?????? ????????????????????? ????????????

                                            adapter.notifyDataSetChanged();  // ????????? ?????? ??? ????????????
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
                                } else if (position == 3) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_necessary_4").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot snapshot, @Nullable String previousChildName) {
                                            User user = snapshot.getValue(User.class); // ???????????? User ????????? ???????????? ?????????.
                                            arrayList.add(user); //?????? ??????????????? ?????????????????? ?????? ????????????????????? ????????????

                                            adapter.notifyDataSetChanged();  // ????????? ?????? ??? ????????????
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
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        arrayList.clear();
                        adapter = new CustomAdapter(arrayList, getActivity());
                        recyclerView.setAdapter(adapter); //????????????????????? ???????????????

                    } else if (position == 1) {

                        spn_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override

                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                arrayList.clear();
                                adapter = new CustomAdapter(arrayList, getActivity());
                                recyclerView.setAdapter(adapter); //????????????????????? ???????????????
                                if (position == 0) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_select_1").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot snapshot, @Nullable String previousChildName) {

                                            User user = snapshot.getValue(User.class); // ???????????? User ????????? ???????????? ?????????.
                                            arrayList.add(user); //?????? ??????????????? ?????????????????? ?????? ????????????????????? ????????????

                                            adapter.notifyDataSetChanged();  // ????????? ?????? ??? ????????????
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
                                } else if (position == 1) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_select_2").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot snapshot, @Nullable String previousChildName) {
                                            User user = snapshot.getValue(User.class); // ???????????? User ????????? ???????????? ?????????.
                                            arrayList.add(user); //?????? ??????????????? ?????????????????? ?????? ????????????????????? ????????????

                                            adapter.notifyDataSetChanged();  // ????????? ?????? ??? ????????????
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
                                } else if (position == 2) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_select_3").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot snapshot, @Nullable String previousChildName) {
                                            User user = snapshot.getValue(User.class); // ???????????? User ????????? ???????????? ?????????.
                                            arrayList.add(user); //?????? ??????????????? ?????????????????? ?????? ????????????????????? ????????????

                                            adapter.notifyDataSetChanged();  // ????????? ?????? ??? ????????????
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
                                } else if (position == 3) {
                                    databaseReference.orderByChild("area_grade").equalTo("m_select_4").addChildEventListener(new ChildEventListener() {
                                        @Override
                                        public void onChildAdded(DataSnapshot snapshot, @Nullable String previousChildName) {
                                            User user = snapshot.getValue(User.class); // ???????????? User ????????? ???????????? ?????????.
                                            arrayList.add(user); //?????? ??????????????? ?????????????????? ?????? ????????????????????? ????????????

                                            adapter.notifyDataSetChanged();  // ????????? ?????? ??? ????????????
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
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        arrayList.clear();
                        adapter = new CustomAdapter(arrayList, getActivity());
                        recyclerView.setAdapter(adapter); //????????????????????? ???????????????
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });





//----------------------------------------------------??????, ??????------------------------------------------------------------
            linearLayoutManager = new VariableScrollSpeedLinearLayoutManager(getActivity(), 100); // ????????? ?????? ??????
            return view;
        }


        return null;
    }
}










