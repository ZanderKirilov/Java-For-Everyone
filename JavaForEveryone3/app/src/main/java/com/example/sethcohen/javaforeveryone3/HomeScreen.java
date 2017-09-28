package com.example.sethcohen.javaforeveryone3;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import DBpack.LoginDataBaseAdapter;
import User.User;

public class HomeScreen extends AppCompatActivity {

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        currentUser = (User)getIntent().getSerializableExtra("User");

        String username = currentUser.getUsername();

        gotoIntroduction(currentUser);
        gotoConditionsAndLoops(currentUser);
        gotoArrays();
        goToProfile(currentUser);
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

    public void gotoIntroduction(final User user){
        Button registerPage = (Button) findViewById(R.id.btn_intro_stages);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gotoIntro = new Intent(HomeScreen.this, Introduction.class);
                gotoIntro.putExtra("User", user);
                startActivity(gotoIntro);
            }
        });
    }



    public void gotoConditionsAndLoops(final User user){
        Button registerPage = (Button) findViewById(R.id.btn_loops_stages);

            registerPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (user.getCurrent_stage().equals("Introduction")){
                        final AlertDialog.Builder noAcess = new AlertDialog.Builder(HomeScreen.this);
                        noAcess.setTitle("No Access");
                        noAcess.setMessage("You don't have permission to this course");
                        noAcess.setIcon(R.drawable.it_talents_logo_inner);
                        noAcess.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        final AlertDialog alert = noAcess.create();
                        alert.show();
                        return;
                    }else {
                        Intent goToLoops = new Intent(HomeScreen.this, ConditionsAndLoops.class);
                        goToLoops.putExtra("User", user);
                        startActivity(goToLoops);
                    }
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

  public void goToProfile(final User user){
        Button profileView = (Button) findViewById(R.id.btn_profile_stages);
        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goToProfile = new Intent(HomeScreen.this, Profile.class);
                goToProfile.putExtra("User", user);
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
