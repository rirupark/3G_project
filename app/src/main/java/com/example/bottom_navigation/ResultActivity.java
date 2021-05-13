package com.example.bottom_navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ResultActivity extends AppCompatActivity {

    private TextView user_name2;
    private ImageView user_img2;
    private TextView user_mail2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        String photoUrl = intent.getStringExtra("photoUrl");
        String email = intent.getStringExtra("email");

        user_name2 = findViewById(R.id.user_name2);
        user_name2.setText(nickName);

        user_mail2 = findViewById(R.id.user_mail2);
        user_mail2.setText(email);

        user_img2 = findViewById(R.id.user_img2);
        Glide.with(this).load(photoUrl).into(user_img2);


        Button imageButton = (Button) findViewById(R.id.btn_start_3g);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }

}