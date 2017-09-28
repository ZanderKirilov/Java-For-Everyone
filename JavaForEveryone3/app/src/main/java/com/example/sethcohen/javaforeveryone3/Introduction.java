package com.example.sethcohen.javaforeveryone3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Introduction extends AppCompatActivity {

    private Integer[] imagesIntro = {R.drawable.intro2fortheappandittalents, R.drawable.intro3thirdpage, R.drawable.intro4primitives, R.drawable.intro5primitivesecond, R.drawable.intro6moreonprimmitives, R.drawable.intro7moreonprimitives, R.drawable.intro8spesialsigns, R.drawable.intro9operations, R.drawable.intro10moreoperations, R.drawable.intro11scanner, R.drawable.intro12incrementdecrement};
    private ImageView imgSwIntro;
    private int currentImamge = 0;
    private Button nextSlide_intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        imgSwIntro = (ImageView) findViewById(R.id.is_picchanger_intro);


        nextSlide_intro = (Button) findViewById(R.id.btn_next_1_introduction);

        nextSlide_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSwIntro.setImageResource(imagesIntro[currentImamge]);
                currentImamge++;
                //Tozi if vrushta snimkite ot nachalo
                if (currentImamge == imagesIntro.length) {
                        currentImamge = 0;
                    }
            }
        });


    }
}
