package com.example.sethcohen.javaforeveryone3;

import android.content.Intent;
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
        username = (EditText)findViewById(R.id.editTextEmail);
        password = (EditText)findViewById(R.id.editTextPassword);
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
            Toast.makeText(this, "Processing", Toast.LENGTH_LONG).show();

            Intent testing = new Intent(Login.this, HomeScreen.class);
            startActivity(testing);
        }else{
            Toast.makeText(this,"Wrong input", Toast.LENGTH_LONG).show();
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
