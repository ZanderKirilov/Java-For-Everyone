package com.example.sethcohen.javaforeveryone3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public Button registerHome;

    public void signUp(){
        registerHome = (Button) findViewById(R.id.btn_RegisterHome);
        registerHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, Register.class);
                startActivity(register);
            }
        });
    }
    public Button signIn;
    public void signIn(){
        signIn = (Button) findViewById(R.id.btn_SignIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(MainActivity.this, Login.class);
                startActivity(login);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signUp();
        signIn();

    }

    public void login(){
    Button mRegisterHome = (Button) findViewById(R.id.btn_RegisterHome);
        mRegisterHome.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.activity_register, null);
            final EditText mEmail = (EditText) mView.findViewById(R.id.Username_Login);
            final EditText mPassword = (EditText) mView.findViewById(R.id.Password_Login);
            Button mSignIn = (Button) mView.findViewById(R.id.btn_SignIn);
            Button mRegisterHome = (Button) mView.findViewById(R.id.btn_RegisterHome);

            mSignIn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if(!mEmail.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this,
                                R.string.success_login_msg,
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this,
                                R.string.error_login_msg,
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
            mBuilder.setView(mView);
            AlertDialog dialog= mBuilder.create();
            dialog.show();
        }
    });
}

    public void websiteITTalents(View view){
        final ImageButton iTTalents = (ImageButton) findViewById(R.id.imageButton);

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

}
