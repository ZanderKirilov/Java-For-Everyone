package com.example.sethcohen.javaforeveryone3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Strings extends AppCompatActivity {
    private Integer[] imagesStrings = {R.drawable.string2stackheap, R.drawable.string3stringpool, R.drawable.string4stringequality, R.drawable.string5stringandequals, R.drawable.string6methods, R.drawable.string7stringbuider};
    private ImageView imgStrings;
    private int currentImamge = 0;
    private Button nextSlide_strings;
    private Button prevSlide_strings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strings);

        imgStrings = (ImageView) findViewById(R.id.is_picchanger_strings);
        nextSlide_strings = (Button) findViewById(R.id.btn_next_1_strings);

        nextSlide_strings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgStrings.setImageResource(imagesStrings[currentImamge]);
                currentImamge++;
                if(currentImamge == imagesStrings.length){
                    currentImamge = 0;
                }

            }
        });

        prevSlide_strings = (Button) findViewById(R.id.btn_prev_1_strings);

        prevSlide_strings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgStrings.setImageResource(imagesStrings[currentImamge]);
                currentImamge--;
                if(currentImamge == imagesStrings.length){
                    currentImamge = 0;
                }

            }
        });
    }
}
