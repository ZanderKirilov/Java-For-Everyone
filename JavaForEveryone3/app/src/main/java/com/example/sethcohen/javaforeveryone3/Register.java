package com.example.sethcohen.javaforeveryone3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    Button register_register;
    EditText username_register;
    EditText password_register;
    EditText retyped_password_register;
    EditText email_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_register = (Button)findViewById(R.id.btn_RegisterHome_Register);
        username_register = (EditText)findViewById(R.id.Username_Register);
        password_register = (EditText)findViewById(R.id.Password_Register);
        retyped_password_register = (EditText)findViewById(R.id.RetypePassword_Register);
        email_register = (EditText)findViewById(R.id.Email_Register);
        register_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    public void login(){
        String user = username_register.getText().toString().trim();
        String pass = password_register.getText().toString().trim();
        String retPass = retyped_password_register.getText().toString().trim();
        String email = email_register.getText().toString().trim();
        if(user.equals("admin") && pass.equals("admin") && email.equals("admin") && retPass.equals(pass)){
            AlertDialog.Builder welcomeAlert = new AlertDialog.Builder(Register.this);
            welcomeAlert.setTitle("ITTalents - JavaForEveryone");
            welcomeAlert.setMessage("Welcome " + user);
            welcomeAlert.setPositiveButton("Go!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent testing = new Intent(Register.this, HomeScreen.class);
                    startActivity(testing);
                }
            });
            welcomeAlert.show();
        }else{
            Toast.makeText(this,"Wrong input", Toast.LENGTH_LONG).show();
        }

    }



}
