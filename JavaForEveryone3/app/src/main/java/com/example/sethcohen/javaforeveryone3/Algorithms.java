package com.example.sethcohen.javaforeveryone3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import DBpack.LoginDataBaseAdapter;
import User.User;
import tests.AlgorithmsTEST;
import tests.CondAndLoopsTEST;

public class Algorithms extends AppCompatActivity {
    private Integer[] imagesAlgorithms = {R.drawable.algorithm2complexity, R.drawable.algorithm3bigo, R.drawable.algorithm4o1, R.drawable.algorithm5on, R.drawable.algorithm6ologn, R.drawable.algorithm7nlogn, R.drawable.algrithm8last, R.drawable.empty_pic};
    private ImageView imgAlgorithms;
    private int currentImamge = 0;
    private Button nextSlide_algorithms;
    private Button prevSlide_algorithms;
    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithms);
        currentUser = (User)getIntent().getSerializableExtra("User");
        logDBAdp=new LoginDataBaseAdapter(this);
        logDBAdp=logDBAdp.open();


        imgAlgorithms = (ImageView) findViewById(R.id.is_picchanger_algorithms);
        nextSlide_algorithms = (Button) findViewById(R.id.btn_next_1_algorithms);

        nextSlide_algorithms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgAlgorithms.setImageResource(imagesAlgorithms[currentImamge]);
                currentImamge++;
                if (currentImamge == imagesAlgorithms.length-1){
                    nextSlide_algorithms.setText("КРАЙ");
                }
                if (currentImamge >= imagesAlgorithms.length) {
                    nextSlide_algorithms.setClickable(false);
                    if (!currentUser.getCurrent_stage().equalsIgnoreCase("Алгоритми")){
                        final AlertDialog.Builder testDone = new AlertDialog.Builder(Algorithms.this,android.R.style.Theme_Holo_Dialog_MinWidth);
                        testDone.setTitle("ITTalents - JavaЗаВсеки");
                        testDone.setMessage("\tВие успешно сте преминали теста за \n Алгоритми,\n искате ли да го посетите отново?");
                        testDone.setIcon(R.drawable.it_talents_logo_inner);
                        testDone.setCancelable(false);
                        testDone.setPositiveButton("Да, Към теста!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent goingToLoops = new Intent(Algorithms.this, AlgorithmsTEST.class);// Change Here!
                                Toast.makeText(Algorithms.this,"Продължаваме към теста за Алгоритми...",Toast.LENGTH_SHORT).show();
                                goingToLoops.putExtra("User", currentUser);
                                startActivity(goingToLoops);
                            }
                        });
                        testDone.setNegativeButton("Не, Към Етапи!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent goingToHome = new Intent(Algorithms.this, HomeScreen.class);
                                goingToHome.putExtra("User", currentUser);
                                startActivity(goingToHome);
                            }
                        });

                        testDone.show();
                    }else{
                        final AlertDialog.Builder goToLoopsLog = new AlertDialog.Builder(Algorithms.this,android.R.style.Theme_Holo_Dialog_MinWidth);
                        goToLoopsLog.setTitle("ITTalents - JavaЗаВсеки");
                        goToLoopsLog.setMessage("\tЖелаете ли да отворите теста за \n Алгоритми?");
                        goToLoopsLog.setIcon(R.drawable.it_talents_logo_inner);
                        goToLoopsLog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent goingToLoops = new Intent(Algorithms.this, AlgorithmsTEST.class);// Change Here!
                                Toast.makeText(Algorithms.this,"Продължаваме към теста за Алгоритми...",Toast.LENGTH_SHORT).show();
                                goingToLoops.putExtra("User", currentUser);
                                startActivity(goingToLoops);
                            }
                        });
                        goToLoopsLog.setNegativeButton("Не", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent goingToHome = new Intent(Algorithms.this, HomeScreen.class);
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

//        prevSlide_algorithms = (Button) findViewById(R.id.btn_prev_1_algorithms);
//
//        prevSlide_algorithms.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                imgAlgorithms.setImageResource(imagesAlgorithms[currentImamge]);
//                currentImamge--;
//                if(currentImamge == imagesAlgorithms.length){
//                    currentImamge = 0;
//                }
//
//            }
//        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent goBack = new Intent(Algorithms.this, HomeScreen.class);
        goBack.putExtra("User", currentUser);
        logDBAdp.close();
        finish();
        startActivity(goBack);
    }
}
