package com.example.sethcohen.javaforeveryone3;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import User.User;

/**
 * Created by HP on 7.10.2017 г..
 */

public class Stages extends AppCompatActivity{

    public void switchPic(final ImageView img, final Integer [] allImages, final Button btnNextSlide,final User currentUser,final Context currentContext,final Class test){

        final String [] stageInfo = currentContext.toString().split("\n");
        final String currentStage = stageInfo[0];
        final String previousStage = stageInfo[1];
        final String nextStage = stageInfo[2];

        btnNextSlide.setOnClickListener(new View.OnClickListener() {
            int currentImage = 0;
            @Override
            public void onClick(View view) {
                img.setImageResource(allImages[currentImage]);
                currentImage++;
                if (currentImage == allImages.length-1){
                    btnNextSlide.setText("КРАЙ");
                }
                if (currentImage >= allImages.length) {
                    btnNextSlide.setClickable(false);
                    if (!currentUser.getCurrent_stage().equalsIgnoreCase(currentStage)){
                        final AlertDialog.Builder testDone = new AlertDialog.Builder(currentContext,android.R.style.Theme_Holo_Dialog_MinWidth);
                        testDone.setTitle("ITTalents - JavaЗаВсеки");
                        testDone.setMessage("\tВие успешно сте преминали теста за \n " + currentStage + ",\n искате ли да го посетите отново?");
//                        testDone.setMessage("Вие успешно сте преминали теста за " + currentStage +
//                                ", искате ли да го посетите отново?\n Предишен етап : " + previousStage + " следващ " + nextStage);
                        testDone.setIcon(R.drawable.it_talents_logo_inner);
                        testDone.setCancelable(false);
                        testDone.setPositiveButton("Да, Към теста!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent goingToLoops = new Intent(currentContext, test);// Change Here!
                                Toast.makeText(currentContext,"Продължаваме към теста за " + currentStage + "...",Toast.LENGTH_SHORT).show();
                                goingToLoops.putExtra("User", currentUser);
                                startActivity(goingToLoops);
                            }
                        });
                        testDone.setNegativeButton("Не, Към Етапи!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent goingToHome = new Intent(currentContext, HomeScreen.class);
                                goingToHome.putExtra("User", currentUser);
                                startActivity(goingToHome);
                            }
                        });

                        testDone.show();
                    }else{
                        final AlertDialog.Builder goToLoopsLog = new AlertDialog.Builder(currentContext,android.R.style.Theme_Holo_Dialog_MinWidth);
                        goToLoopsLog.setTitle("ITTalents - JavaЗаВсеки");
                        goToLoopsLog.setMessage("\tЖелаете ли да отворите теста за \n" + currentStage);
                        goToLoopsLog.setIcon(R.drawable.it_talents_logo_inner);
                        goToLoopsLog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent goingToLoops = new Intent(currentContext, test);// Change Here!
                                Toast.makeText(currentContext,"Продължаваме към теста за \n" + currentStage + "...",Toast.LENGTH_SHORT).show();
                                goingToLoops.putExtra("User", currentUser);
                                startActivity(goingToLoops);
                            }
                        });
                        goToLoopsLog.setNegativeButton("Не", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent goingToHome = new Intent(currentContext, HomeScreen.class);
                                goingToHome.putExtra("User", currentUser);
                                startActivity(goingToHome);
                            }
                        });
                        goToLoopsLog.setCancelable(false);
                        goToLoopsLog.show();
                    }
                }


            }
        });

    }

    }


