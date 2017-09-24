package com.example.sethcohen.javaforeveryone3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Bundle gettingData = getIntent().getExtras();
        String user = gettingData.getString("Username");
        EditText username = (EditText)findViewById(R.id.username_profile);
        username.setText(user);
    }
}
