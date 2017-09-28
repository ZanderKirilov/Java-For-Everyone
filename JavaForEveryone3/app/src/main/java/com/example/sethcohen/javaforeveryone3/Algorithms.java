package com.example.sethcohen.javaforeveryone3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Algorithms extends AppCompatActivity {
    private Integer[] imagesAlgorithms = {R.drawable.algorithm2complexity, R.drawable.algorithm3bigo, R.drawable.algorithm4o1, R.drawable.algorithm5on, R.drawable.algorithm6ologn, R.drawable.algorithm7nlogn, R.drawable.algrithm8last};
    private ImageView imgAlgorithms;
    private int currentImamge = 0;
    private Button nextSlide_algorithms;
    private Button prevSlide_algorithms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithms);


        imgAlgorithms = (ImageView) findViewById(R.id.is_picchanger_algorithms);
        nextSlide_algorithms = (Button) findViewById(R.id.btn_next_1_algorithms);

        nextSlide_algorithms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgAlgorithms.setImageResource(imagesAlgorithms[currentImamge]);
                currentImamge++;
                if(currentImamge == imagesAlgorithms.length){
                    currentImamge = 0;
                }

            }
        });

        prevSlide_algorithms = (Button) findViewById(R.id.btn_prev_1_algorithms);

        prevSlide_algorithms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgAlgorithms.setImageResource(imagesAlgorithms[currentImamge]);
                currentImamge--;
                if(currentImamge == imagesAlgorithms.length){
                    currentImamge = 0;
                }

            }
        });
    }
}
