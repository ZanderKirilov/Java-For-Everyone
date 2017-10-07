package com.example.sethcohen.javaforeveryone3;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import User.User;

import static com.example.sethcohen.javaforeveryone3.R.color.colorBorder;

public class Achievements extends AppCompatActivity {

    private User currentUser;
    //Просто пример за постижение
    TextView achievement;
    TextView achievementInfo;
    ImageView badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        currentUser = (User)getIntent().getSerializableExtra("User");

        achievement = (TextView)findViewById(R.id.code_master_ach_achievements);
        achievementInfo = (TextView)findViewById(R.id.code_master_ach_info_achievements);
        badge = (ImageView)findViewById(R.id.img_badge3_achievements);
        if (currentUser.getLast_achievement().equalsIgnoreCase(achievement.getText().toString())){
            achievement.setTextColor(Color.rgb(122,203,255));
            achievementInfo.setTextColor(Color.rgb(122,203,255));
            badge.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent goBack = new Intent(Achievements.this, HomeScreen.class);
        goBack.putExtra("User", currentUser);
        finish();
        startActivity(goBack);
    }
}
