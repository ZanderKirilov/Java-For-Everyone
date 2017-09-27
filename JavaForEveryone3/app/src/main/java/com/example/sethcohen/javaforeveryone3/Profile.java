package com.example.sethcohen.javaforeveryone3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.AutoText;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        String username = getIntent().getStringExtra("username");
        TextView usernameET = (TextView)findViewById(R.id.username_profile);
        usernameET.setText(username);

    }
}
