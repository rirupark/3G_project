package com.example.bottom_navigation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.common.net.InternetDomainName;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private SignInButton btn_google; // 구글 로그인 버튼
    private FirebaseAuth auth; // 파베 인증 객체
    private GoogleApiClient googleApiClient; // 구글 API 클라이언트 객체
    private static final int REQ_SIGN_GOOGLE = 100; // 구글 로그인 결과 코드(임시로 선언)
    private DatabaseReference mDatabase;
    private String grade_num;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) { // 앱이 실행될 때 처음 수행되는 곳
        super.onCreate(savedInstanceState);

        //상단바 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_login);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build(); //기본 옵션 세팅

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

        auth = FirebaseAuth.getInstance(); // 파이어베이스 인증 객체 초기화.
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btn_google = findViewById(R.id.btn_google_login);
        btn_google.setOnClickListener(new View.OnClickListener() { // 구글 로그인 버튼 클릭 시 이곳을 수행.
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, REQ_SIGN_GOOGLE);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // 구글 로그인 인증 요청했을 때, 결과 값 되돌려 받는 곳.
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_SIGN_GOOGLE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) { // 인증결과가 성공적이면...
                GoogleSignInAccount account = result.getSignInAccount(); // account 라는 데이터는 구글로그인 정보를 담고 있음. (닉네임, 프로필 사진, 이메일 주소 등)
                resultLogin(account); // 로그인 결과값 출력 수행 메소드
            }
        }

    }

    private void resultLogin(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) { // 로그인이 성공했으면 ...

                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            UserAccount account1 = new UserAccount();

                            Toast.makeText(Login.this, "로그인 성공", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                                intent.putExtra("nickName", account.getDisplayName());
                                intent.putExtra("email", account.getEmail());
                                intent.putExtra("photoUrl", String.valueOf(account.getPhotoUrl())); // 특정 자료형을 String으로 변환.
                                startActivity(intent);

                                if (firebaseUser.getUid().toString() != firebaseUser.getIdToken(true).toString()) { //로그인 할 때마다 데이터 베이스에 사용자계정이 중복되어 저장되는 것을 막아준다.
                                    account1.setIdToken(firebaseUser.getUid());
                                    account1.setEmailId(firebaseUser.getEmail());

                                    mDatabase.child("UserInfo").child(firebaseUser.getUid()).setValue(account1);
                                    Log.e("spn", "resultactivity : " + mDatabase.child("UserInfo").child(firebaseUser.getUid()).child("std_grade_num").getKey().isEmpty());
                                }


                                //grade_num = account1.getStd_grade_num();

//                                if (mDatabase.child("UserInfo").child(firebaseUser.getUid()).child("std_grade_num").getKey().contains("null")){
//
//                                    Log.e("fff", "grade : "+ mDatabase.child("UserInfo").child(firebaseUser.getUid()).child("std_grade_num").getKey());
//
//                                    Toast.makeText(Login.this, "로그인 성공", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
//                                    intent.putExtra("nickName", account.getDisplayName());
//                                    intent.putExtra("email", account.getEmail());
//                                    intent.putExtra("photoUrl", String.valueOf(account.getPhotoUrl())); // 특정 자료형을 String으로 변환.
//                                    startActivity(intent);
//
//
//                                }
//
//
//                                 else {
//                                    Log.e("fff", "grade : "+ mDatabase.child("UserInfo").child(firebaseUser.getUid()).child("std_grade_num").getKey());
//                                    Toast.makeText(Login.this, "로그인 성공", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                    startActivity(intent);
//                                }

                            } else { // 로그인이 실패했으면 ...
                                Toast.makeText(Login.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                            }
                        }


                });

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

    /*
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        mAuth.signInWithCredential(credential)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.v("알림", "ONCOMPLETE");

                        if (!task.isSuccessful()) {

                            Log.v("알림", "!task.isSuccessful()");

                            Toast.makeText(Login.this, "인증에 실패하였습니다.", Toast.LENGTH_SHORT).show();

                        } else {

                            Log.v("알림", "task.isSuccessful()");

                            FirebaseUser user = mAuth.getCurrentUser();


                            String cu = mAuth.getUid();

                            String name = user.getDisplayName();

                            String email = user.getEmail();

                            String photoUrl = user.getPhotoUrl().toString();

                            String phone = user.getPhoneNumber();


                            Log.v("알림", "현재로그인한 유저 " + cu);

                            Log.v("알림", "현재로그인한 이메일 " + email);

                            Log.v("알림", "유저 이름 " + name);

                            Log.v("알림", "유저 사진 " + photoUrl);

                            Log.v("알림", "유저 폰 " + phone);


                            //이 부분이 DB에 데이터 저장

                            UserData userdata = new UserData(name, photoUrl);

                            mDatabase.child("users").child(cu).setValue(userdata);

                            //이 부분이 DB에 데이터 저장


                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                            startActivity(intent);

                            Toast.makeText(Login.this, "FireBase 아이디 생성이 완료 되었습니다", Toast.LENGTH_SHORT).show();

                        }


                    }

                });


    }

     */


