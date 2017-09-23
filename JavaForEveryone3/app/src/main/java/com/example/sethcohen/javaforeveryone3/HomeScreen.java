package com.example.sethcohen.javaforeveryone3;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        gotoIntroduction();
        gotoConditionsAndLoops();
        gotoArrays();
    }

    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(HomeScreen.this);
        dialog.setContentView(R.layout.dialog_exit);
        dialog.setTitle("Leaving so soon..?");
        Button exitBtnYes = (Button)dialog.findViewById(R.id.btn_Exit_Yes);
        Button exitBtnNO = (Button)dialog.findViewById(R.id.btn_Exit_NO);
        exitBtnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        dialog.show();
        exitBtnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }

    public void gotoIntroduction(){
        Button registerPage = (Button) findViewById(R.id.button_Intro);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gotoIntro = new Intent(HomeScreen.this, Introduction.class);
                startActivity(gotoIntro);
            }
        });
    }



    public void gotoConditionsAndLoops(){
        Button registerPage = (Button) findViewById(R.id.button2_Loops);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent register = new Intent(HomeScreen.this, ConditionsAndLoops.class);
                startActivity(register);
            }
        });
    }

    public void gotoArrays(){
        Button registerPage = (Button) findViewById(R.id.button3_Arrays);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent register = new Intent(HomeScreen.this, Arrays.class);
                startActivity(register);
            }
        });
    }

    public void websiteITTalents(View view){
        final ImageButton iTTalents = (ImageButton) findViewById(R.id.imageButton2);

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
