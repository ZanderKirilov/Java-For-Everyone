package com.example.sethcohen.javaforeveryone3;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.camera2.params.BlackLevelPattern;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button login;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerButton();
        login = (Button)findViewById(R.id.btn_Login_Login);
        username = (EditText)findViewById(R.id.Username_Login);
        password = (EditText)findViewById(R.id.Password_Login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });




    }

    public void successfulAppEnter(String user){
        final AlertDialog.Builder welcomeAlert = new AlertDialog.Builder(Login.this);
        welcomeAlert.setTitle("ITTalents - JavaForEveryone");
        welcomeAlert.setMessage("\tWelcome " + user);
        final AlertDialog alert = welcomeAlert.create();
        alert.show();
        new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                alert.dismiss();
                Intent testing = new Intent(Login.this, HomeScreen.class);
                startActivity(testing);
            }
        }.start();
    }
    public void unSuccessfulAppEnter(){
        Toast.makeText(this,"Wrong input", Toast.LENGTH_LONG).show();
    }


    public void login(){
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if(user.equals("admin") && pass.equals("admin")){
            successfulAppEnter(user);
        }else{
            unSuccessfulAppEnter();
        }

    }

    public void registerButton(){
        Button registerPage = (Button) findViewById(R.id.btn_RegisterHome_Login);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(Login.this, Register.class);
                startActivity(register);
            }
        });
    }

}
