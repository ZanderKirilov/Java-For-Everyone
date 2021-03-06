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
import User.User;

public class MainActivity extends AppCompatActivity {
    private LoginDataBaseAdapter loginDataBaseAdp;
    private User currentUser;
    private Button registerHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginDataBaseAdp=new LoginDataBaseAdapter(this);
        loginDataBaseAdp=loginDataBaseAdp.open();
        signUp();
        websiteITTalents();
    }


    public void signUp(){
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
        dialog.setTitle("Влезте в " + getString(R.string.app_name));
        final EditText editTextUserName=(EditText)dialog.findViewById(R.id.username_dialoglogin);
        final EditText editTextPassword=(EditText)dialog.findViewById(R.id.password_newpass);

        Button btnSignIn=(Button)dialog.findViewById(R.id.btn_login_dialoglogin);

        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();
                String storedPassword=loginDataBaseAdp.getUserPassword(userName);
                String user_email = loginDataBaseAdp.getUserEmail(userName);
                String user_stage = loginDataBaseAdp.getUserStage(userName);
                int user_points = loginDataBaseAdp.getUserPoints(userName);
                currentUser = new User(userName,user_email, user_stage, user_points);

                if(password.equals(storedPassword)){
                    Toast.makeText(MainActivity.this, "Влизането Успешно", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    Intent goneToHome = new Intent(MainActivity.this, HomeScreen.class);
                    goneToHome.putExtra("User", currentUser);
                    startActivity(goneToHome);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, "Потребителското име или Паролата, не съвпадат", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }



    public void websiteITTalents(){
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
