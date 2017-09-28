package com.example.sethcohen.javaforeveryone3;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DBpack.LoginDataBaseAdapter;

public class Login extends AppCompatActivity {
    private Button login;
    private EditText username;
    private EditText password;
    private LoginDataBaseAdapter logDBAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        registerButton();
        String tempEmail = "";
        forgottenPassword(tempEmail);

        login = (Button)findViewById(R.id.btn_Login_Login);
        username = (EditText)findViewById(R.id.username_login);
        password = (EditText)findViewById(R.id.password_login);
        logDBAdp=new LoginDataBaseAdapter(this);
        logDBAdp=logDBAdp.open();


    }


    public void successfulAppEnter(final String user){
        final AlertDialog.Builder welcomeAlert = new AlertDialog.Builder(Login.this);
        welcomeAlert.setTitle("ITTalents - JavaForEveryone");
        welcomeAlert.setMessage("\tWelcome, " + user);
        welcomeAlert.setIcon(R.drawable.it_talents_logo_inner);
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
                testing.putExtra("username", user);
                startActivity(testing);
                finish();
            }
        }.start();
    }
    public void unSuccessfulAppEnter(){
        Toast.makeText(this,"Wrong input", Toast.LENGTH_LONG).show();
    }


    public void login(View V){
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String storedPass = logDBAdp.getUserPassword(user);

        if(pass.equals(storedPass)){
            successfulAppEnter(user);
        }else{
            unSuccessfulAppEnter();
        }

    }

    public void registerButton(){
        Button registerPage = (Button) findViewById(R.id.btn_register_login);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(Login.this, Register.class);
                startActivity(register);
            }
        });
    }

    public void forgottenPassword(String email){

        TextView frgtPass = (TextView) findViewById(R.id.frgtn_pass);
        frgtPass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder frgtBuilder = new AlertDialog.Builder(Login.this);
                View frgtView = getLayoutInflater().inflate(R.layout.activity_login, null);
                Button send = frgtView.findViewById(R.id.frgtn_pass);

                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //TO DO check email in DB
//                        "Please enter your Registered Email: "
//                        if(){
//                        }else {
//                        }
                    }
                });
                frgtBuilder.setView(frgtView);
                AlertDialog dialog = frgtBuilder.create();
                dialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logDBAdp.close();
    }
}
