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

public class Algorithms extends Stages {
    private Integer[] imagesAlgorithms = {R.drawable.algorithm2complexity, R.drawable.algorithm3bigo, R.drawable.algorithm4o1, R.drawable.algorithm5on, R.drawable.algorithm6ologn, R.drawable.algorithm7nlogn, R.drawable.algrithm8last, R.drawable.empty_pic};
    private ImageView imgAlgorithms;
    private Button nextSlide_algorithms;
    private Button prevSlide_algorithms;
    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;

    protected String className = "Алгоритми\nСимволни Низове\nМатрицата";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithms);
        currentUser = (User)getIntent().getSerializableExtra("User");
        logDBAdp=new LoginDataBaseAdapter(this);
        logDBAdp=logDBAdp.open();


        imgAlgorithms = (ImageView) findViewById(R.id.is_picchanger_algorithms);
        nextSlide_algorithms = (Button) findViewById(R.id.btn_next_1_algorithms);


        switchPic(imgAlgorithms,imagesAlgorithms,nextSlide_algorithms,currentUser,Algorithms.this,AlgorithmsTEST.class);

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
    public String toString() {
        return this.className;
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
