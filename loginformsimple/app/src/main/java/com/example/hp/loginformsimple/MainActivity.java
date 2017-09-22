package com.example.hp.loginformsimple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.LoginBTN);
        username = (EditText)findViewById(R.id.username_log);
        password = (EditText)findViewById(R.id.pass_log);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }
    public void login(){
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if(user.equals("admin") && pass.equals("admin")){
            Toast.makeText(this, "username and pass are correct", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Wrong input", Toast.LENGTH_LONG).show();
        }

    }

}
