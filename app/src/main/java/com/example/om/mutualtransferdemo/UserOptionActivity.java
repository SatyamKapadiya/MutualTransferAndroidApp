package com.example.om.mutualtransferdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class UserOptionActivity extends AppCompatActivity {

    ImageView imgMatching,imgMyProfile;
    TextView txtMatching,txtMyProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_option);

        imgMatching= (ImageView) findViewById(R.id.imgMatching);
        imgMyProfile= (ImageView) findViewById(R.id.imgMyProfile);
        txtMatching= (TextView) findViewById(R.id.txtMatching);
        txtMyProfile= (TextView) findViewById(R.id.txtMyProfile);

        imgMatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserOptionActivity.this,MatchingActivity.class));

            }
        });

        txtMatching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserOptionActivity.this,MatchingActivity.class));
            }
        });

        imgMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserOptionActivity.this,MyProfileActivity.class));

            }
        });
        txtMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserOptionActivity.this,MyProfileActivity.class));
            }
        });
    }
}
