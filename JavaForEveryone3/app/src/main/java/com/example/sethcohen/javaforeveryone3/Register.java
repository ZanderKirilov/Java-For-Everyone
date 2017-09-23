package com.example.sethcohen.javaforeveryone3;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DBpack.LoginDataBaseAdapter;

public class Register extends Login {
    EditText editTextUserName,editTextPassword,editTextConfirmPassword;
    Button btnCreateAccount;

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        editTextUserName=(EditText)findViewById(R.id.Username_Register);
        editTextPassword=(EditText)findViewById(R.id.Password_Register);
        editTextConfirmPassword=(EditText)findViewById(R.id.RetypePassword_Register);

        btnCreateAccount=(Button)findViewById(R.id.btn_RegisterHome_Register);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();

                if(userName.equals("")||password.equals("")||confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    loginDataBaseAdapter.insertEntry(userName, password);
                    Toast.makeText(getApplicationContext(), "Account Successfully Created - Log in now", Toast.LENGTH_LONG).show();
                    Intent goneToLogin = new Intent(Register.this, Login.class);
                    startActivity(goneToLogin);
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdapter.close();
    }

}
