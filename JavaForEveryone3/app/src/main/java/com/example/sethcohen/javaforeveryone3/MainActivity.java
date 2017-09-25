package com.example.sethcohen.javaforeveryone3;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import DBpack.LoginDataBaseAdapter;

public class MainActivity extends AppCompatActivity {
    private LoginDataBaseAdapter loginDataBaseAdp;

    private Button registerHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginDataBaseAdp=new LoginDataBaseAdapter(this);
        loginDataBaseAdp=loginDataBaseAdp.open();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    public void signUp(View v){
        registerHome = (Button) findViewById(R.id.btn_register_main);
        registerHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, Register.class);
                startActivity(register);
            }
        });
    }

    public void signIn(View V)
    {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_login);
        dialog.setTitle("Login To " + getString(R.string.app_name));
        final EditText editTextUserName=(EditText)dialog.findViewById(R.id.username_dialoglogin);
        final EditText editTextPassword=(EditText)dialog.findViewById(R.id.password_dialoglogin);

        Button btnSignIn=(Button)dialog.findViewById(R.id.btn_login_dialoglogin);

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                String storedPassword=loginDataBaseAdp.getSinlgeEntry(userName);

                if(password.equals(storedPassword)){
                    Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    Intent goneToHome = new Intent(MainActivity.this, HomeScreen.class);
                    startActivity(goneToHome);
                }
                else{
                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }



    public void websiteITTalents(View view){
        final ImageButton iTTalents = (ImageButton) findViewById(R.id.img_btn_ittalents_main);

        new Thread(new Runnable() {
            public void run() {
                iTTalents.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {;
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.ittalents.bg/home"));
                    startActivity(intent);
                }
            })  ;
            }
        }).start();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginDataBaseAdp.close();
    }
}
