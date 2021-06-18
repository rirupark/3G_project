package com.example.bottom_navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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



import java.util.ArrayList;
import java.util.List;

public class Gyoyang extends Fragment {
    private  View view;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<User> arrayList;
    List<UserLearn> learnArrayList = new ArrayList<UserLearn>();

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private LinearLayoutManager linearLayoutManager;

    private String area_tong;
    private int credit_tong;
    private Integer credit_gae;
    private String data_tong;
    private String data_gae;
    static String final_dataTong = "";

    private FirebaseAuth auth; // 파베 인증 객체
    private DatabaseReference mDatabase;
    private DatabaseReference gyoDatabase;
    String mPath;

    private ListView listView;
    private ListView listview2;
    private ListViewAdapter adapterlist;

    public static Gyoyang newinstance(){
        return new Gyoyang();
    }
    public Gyoyang(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_gyoyang,null);

        ImageButton btn_back = (ImageButton)view.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Checklist.newinstance());
            }
        });

        //리사이클러뷰.
        recyclerView = view.findViewById(R.id.re_jeongong2);//아이디 연결
        recyclerView.setHasFixedSize(true);//리사이클러뷰 기존성능강화
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>();//User 객체를 담을 어레이 리스트(어댑터쪽으로)

        listView = view.findViewById(R.id.listview);
        listview2 = view.findViewById(R.id.listview2);
        learnArrayList = new ArrayList<>();






        Spinner spn_gyoyang = (Spinner)view.findViewById(R.id.spn_gyoyang);
        Spinner spn_areatong = (Spinner)view.findViewById(R.id.spn_areagyo);

        Spinner spn_tongcredit = (Spinner)view.findViewById(R.id.tonggyo_credit);
        EditText tong_name = (EditText)view.findViewById(R.id.tong_input);
        ImageButton btn_tong_save = (ImageButton)view.findViewById(R.id.btn_save_check_tong);

        Spinner spn_gaecredit = (Spinner)view.findViewById(R.id.gaegyo_credit);
        EditText gae_name = (EditText)view.findViewById(R.id.gae_input);
        ImageButton btn_gae_save = (ImageButton)view.findViewById(R.id.btn_save_check_gae);

        TextView txt_tongTitle = (TextView)view.findViewById(R.id.txt_title);

        listView = (ListView)view.findViewById(R.id.listview);
        listview2 = (ListView)view.findViewById(R.id.listview2);





        /* ------------------------------------------------통합교양 입력값 DB에 저장하기 ----------------------------------------*/
        //통교 영역 스피너 항목 선택 시 이벤트 ----> else if(position ==3) 안에 넣으면 오류 !!!!!!
        spn_areatong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    area_tong = "문학과 문화 영역";
                }

                else if (position == 1) {
                    area_tong = "역사와 철학 영역";
                }

                else if (position == 2) {
                    area_tong = "인간과 사회 영역";
                }

                else if (position == 3) {
                    area_tong = "생명과 환경 영역";
                }

                else if (position == 4) {
                    area_tong = "과학과 기술 영역";
                }

                else if (position == 5) {
                    area_tong = "예술과 체육 영역";
                }

                else if (position == 6) {
                    area_tong = "융복합 영역";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //통교 학점 스피너 항목 선택 시 이벤트
        spn_tongcredit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    credit_tong = 1;
                }

                else if (position == 1) {
                    credit_tong = 2;
                }

                else if (position == 2) {
                    credit_tong = 3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        auth = FirebaseAuth.getInstance(); // 파이어베이스 인증 객체 초기화.
        mDatabase = FirebaseDatabase.getInstance().getReference();

        gyoDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser firebaseUser = auth.getCurrentUser();


        //통교 체크버튼 클릭 시 입력 텍스트 데이터베이스에 저장하기.

        btn_tong_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                data_tong = tong_name.getText().toString();
                final_dataTong = data_tong;

                //파이어베이스 Realtime database에서 UserInfo테이블 속, tongGyo필드를 생성하고 그 안에 className필드를 생성. 그리고 그 안에 className, area, credit값을 저장한다.
                mDatabase.child("UserInfo").child(firebaseUser.getUid()).child("finish").child(final_dataTong).child("className").setValue(data_tong);
                mDatabase.child("UserInfo").child(firebaseUser.getUid()).child("finish").child(final_dataTong).child("tongArea").setValue(area_tong);
                mDatabase.child("UserInfo").child(firebaseUser.getUid()).child("finish").child(final_dataTong).child("credit").setValue(credit_tong);
                mDatabase.child("UserInfo").child(firebaseUser.getUid()).child("finish").child(final_dataTong).child("area").setValue("tongGyo");
                tong_name.setText(null);




//


                adapterlist.notifyDataSetChanged();

            }
        });


        adapterlist = new ListViewAdapter(getActivity(), new ListViewAdapter.OnDeleteClickListener() {
            @Override
            public void onDelete(View v, int pos) {
                adapterlist.removeItem(pos);

                //RealTime DB에서 해당 과목데이터 삭제.
                FirebaseUser firebaseUser = auth.getCurrentUser();
                DatabaseReference hopperRef = mDatabase.child("UserInfo").child(firebaseUser.getUid()).child("finish").child(final_dataTong);
                hopperRef.removeValue();
            }
        });

        listView.setAdapter(adapterlist);
        listview2.setAdapter(adapterlist);


        /* ---------------------------------------------------------------------------------------------------------------*/



        /* ------------------------------------------------개척교양 입력값 DB에 저장하기 ----------------------------------------*/
        //개교 학점 스피너 항목 선택 시 이벤트
        spn_gaecredit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    credit_gae = 1;
                }

                else if (position == 1) {
                    credit_gae = 2;
                }

                else if (position == 2) {
                    credit_gae = 3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //통교 체크버튼 클릭 시 입력 텍스트 데이터베이스에 저장하기.
        btn_gae_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_gae = gae_name.getText().toString();
                //파이어베이스 Realtime database에서 UserInfo테이블 속, tongGyo필드를 생성하고 그 안에 className필드를 생성. 그리고 그 안에 className, area, credit값을 저장한다.
                mDatabase.child("UserInfo").child(firebaseUser.getUid()).child("finish").child(data_gae).child("className").setValue(data_gae);
                mDatabase.child("UserInfo").child(firebaseUser.getUid()).child("finish").child(data_gae).child("credit").setValue(credit_gae);
                mDatabase.child("UserInfo").child(firebaseUser.getUid()).child("finish").child(data_gae).child("area").setValue("gaeGyo");
                gae_name.setText(null);

                Log.e("data", data_gae);

            }
        });

        /* ---------------------------------------------------------------------------------------------------------------*/




        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동
        databaseReference = database.getReference("User"); // DB테이블 연결






        spn_gyoyang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    LinearLayout tong_linear = (LinearLayout) getActivity().findViewById(R.id.tong_cul);
                    tong_linear.setVisibility(View.INVISIBLE);

                    LinearLayout recyc_list = (LinearLayout) getActivity().findViewById(R.id.recyc_gyo_list);
                    recyc_list.setVisibility(View.VISIBLE);

                    LinearLayout input_window = (LinearLayout) getActivity().findViewById(R.id.input_window);
                    input_window.setVisibility(View.INVISIBLE);

                    LinearLayout input_window_gae = (LinearLayout) getActivity().findViewById(R.id.input_window_gae);
                    input_window_gae.setVisibility(View.INVISIBLE);

                    databaseReference.orderByChild("area").equalTo("a_necessary").addChildEventListener(new ChildEventListener() {
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

                } else if (position == 1) {
                    LinearLayout tong_linear = (LinearLayout) getActivity().findViewById(R.id.tong_cul);
                    tong_linear.setVisibility(View.INVISIBLE);

                    LinearLayout recyc_list = (LinearLayout) getActivity().findViewById(R.id.recyc_gyo_list);
                    recyc_list.setVisibility(View.VISIBLE);

                    LinearLayout input_window = (LinearLayout) getActivity().findViewById(R.id.input_window);
                    input_window.setVisibility(View.INVISIBLE);

                    LinearLayout input_window_gae = (LinearLayout) getActivity().findViewById(R.id.input_window_gae);
                    input_window_gae.setVisibility(View.INVISIBLE);

                    databaseReference.orderByChild("area").equalTo("basic").addChildEventListener(new ChildEventListener() {
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

                } else if (position == 2) {
                    LinearLayout tong_linear = (LinearLayout) getActivity().findViewById(R.id.tong_cul);
                    tong_linear.setVisibility(View.INVISIBLE);

                    LinearLayout recyc_list = (LinearLayout) getActivity().findViewById(R.id.recyc_gyo_list);
                    recyc_list.setVisibility(View.VISIBLE);

                    LinearLayout input_window = (LinearLayout) getActivity().findViewById(R.id.input_window);
                    input_window.setVisibility(View.INVISIBLE);

                    LinearLayout input_window_gae = (LinearLayout) getActivity().findViewById(R.id.input_window_gae);
                    input_window_gae.setVisibility(View.INVISIBLE);

                    databaseReference.orderByChild("area").equalTo("e_learning").addChildEventListener(new ChildEventListener() {
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

                } else if (position == 3) {
                    adapterlist.clear();
                    LinearLayout tong_linear = (LinearLayout) getActivity().findViewById(R.id.tong_cul);
                    tong_linear.setVisibility(View.VISIBLE);

                    LinearLayout recyc_list = (LinearLayout) getActivity().findViewById(R.id.recyc_gyo_list);
                    recyc_list.setVisibility(View.INVISIBLE);

                    LinearLayout input_window = (LinearLayout) getActivity().findViewById(R.id.input_window);
                    input_window.setVisibility(View.VISIBLE);

                    LinearLayout input_window_gae = (LinearLayout) getActivity().findViewById(R.id.input_window_gae);
                    input_window_gae.setVisibility(View.INVISIBLE);

                    mPath = "UserInfo/"+firebaseUser.getUid()+"/finish";
                    gyoDatabase = database.getReference("UserInfo");

                    gyoDatabase.child(firebaseUser.getUid()).child("finish").orderByChild("area").equalTo("tongGyo").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            UserLearn userLearn = snapshot.getValue(UserLearn.class);
                            adapterlist.addItem(userLearn.getTongArea(), userLearn.getClassName(), userLearn.getCredit());


                            adapterlist.notifyDataSetChanged();
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



//


                } else if (position == 4) {
                    adapterlist.clear();
                    LinearLayout tong_linear = (LinearLayout) getActivity().findViewById(R.id.tong_cul);
                    tong_linear.setVisibility(View.INVISIBLE);

                    LinearLayout recyc_list = (LinearLayout) getActivity().findViewById(R.id.recyc_gyo_list);
                    recyc_list.setVisibility(View.INVISIBLE);

                    LinearLayout input_window = (LinearLayout) getActivity().findViewById(R.id.input_window);
                    input_window.setVisibility(View.INVISIBLE);

                    LinearLayout input_window_gae = (LinearLayout) getActivity().findViewById(R.id.input_window_gae);
                    input_window_gae.setVisibility(View.VISIBLE);

                    gyoDatabase.child(firebaseUser.getUid()).child("finish").orderByChild("area").equalTo("gaeGyo").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                            UserLearn userLearn = snapshot.getValue(UserLearn.class);
                            adapterlist.addItem("개척교양", userLearn.getClassName(), userLearn.getCredit());

                            adapterlist.notifyDataSetChanged();
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
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        linearLayoutManager = new VariableScrollSpeedLinearLayoutManager(getActivity(), 100); // 스크롤 속도 조절




        return view;

    }
}