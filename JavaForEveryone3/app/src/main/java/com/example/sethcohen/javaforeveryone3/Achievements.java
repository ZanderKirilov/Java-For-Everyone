package com.example.sethcohen.javaforeveryone3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import User.User;

public class Achievements extends AppCompatActivity {

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);
        currentUser = (User)getIntent().getSerializableExtra("User");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent goBack = new Intent(Achievements.this, HomeScreen.class);
        goBack.putExtra("User", currentUser);
        startActivity(goBack);
    }
}
