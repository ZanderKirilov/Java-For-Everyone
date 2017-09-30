package com.example.sethcohen.javaforeveryone3;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DBpack.LoginDataBaseAdapter;

public class Register extends Login {
    private EditText editTextUserName,editTextPassword,editTextConfirmPassword, editTextEmail;
    private Button btnCreateAccount;
    public static final String emailRegX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    public static final String usernameNpassRegX = "[a-zA-Z0-9\\._\\-]{3,}";
    private String starting_stage = "Въведение";

    LoginDataBaseAdapter loginDataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        editTextUserName=(EditText)findViewById(R.id.Username_Register);
        editTextPassword=(EditText)findViewById(R.id.Password_Register);
        editTextConfirmPassword=(EditText)findViewById(R.id.RetypePassword_Register);
        editTextEmail = (EditText)findViewById(R.id.Email_Register);

        btnCreateAccount=(Button)findViewById(R.id.btn_RegisterHome_Register);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String username=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                String confirmPassword=editTextConfirmPassword.getText().toString();
                String email = editTextEmail.getText().toString();

                if(username.equals("")||password.equals("")||confirmPassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "Моля попълни всичко", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!username.matches(usernameNpassRegX) || !password.matches(usernameNpassRegX)){
                    Toast.makeText(getApplicationContext(), "Потребителското име и паролата, трябва да съдържат само букви и цифри", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Паролите не съвпадат", Toast.LENGTH_LONG).show();
                    EditText passED = (EditText) findViewById(R.id.RetypePassword_Register);
                    passED.setError("Просто напиши това : " + password);
                    return;
                }
                if (!email.matches(emailRegX)){
                    Toast.makeText(getApplicationContext(), "E-mail адреса не е валиден", Toast.LENGTH_LONG).show();
                    EditText emailED = (EditText) findViewById(R.id.Email_Register);
                    emailED.setError("Невалиден e-mail");
                    return;
                }
                else {
                    loginDataBaseAdapter.insertEntry(username, password, email, starting_stage, 10);
                    Toast.makeText(getApplicationContext(), "Профилът е създаден успешно, ВЛЕЗ СЕГА!", Toast.LENGTH_LONG).show();
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
