package com.example.sethcohen.javaforeveryone3;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        String username = getIntent().getStringExtra("username");
        gotoIntroduction();
        gotoConditionsAndLoops();
        gotoArrays();
        goToProfile(username);
        goToAchievements();
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
        Button registerPage = (Button) findViewById(R.id.btn_intro_stages);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gotoIntro = new Intent(HomeScreen.this, Introduction.class);
                startActivity(gotoIntro);
            }
        });
    }



    public void gotoConditionsAndLoops(){
        Button registerPage = (Button) findViewById(R.id.btn_loops_stages);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent register = new Intent(HomeScreen.this, ConditionsAndLoops.class);
                startActivity(register);
            }
        });
    }

    public void gotoArrays(){
        Button registerPage = (Button) findViewById(R.id.btn_arrays_stages);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent register = new Intent(HomeScreen.this, Arrays.class);
                startActivity(register);
            }
        });
    }

  public void goToProfile(final String username){
        Button profileView = (Button) findViewById(R.id.btn_profile_stages);
        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToProfile = new Intent(HomeScreen.this, Profile.class);
                goToProfile.putExtra("username", username);
                startActivity(goToProfile);
            }
        });
    }


    public void goToAchievements(){
        Button registerPage = (Button) findViewById(R.id.btn_achievements_profile);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToAchievements = new Intent(HomeScreen.this, Achievements.class);
                startActivity(goToAchievements);
            }
        });
    }

    public void websiteITTalents(View view){
        final ImageButton iTTalents = (ImageButton) findViewById(R.id.img_btn_ittalents);

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
