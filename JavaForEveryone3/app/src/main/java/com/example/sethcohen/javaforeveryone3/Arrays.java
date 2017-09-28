package com.example.sethcohen.javaforeveryone3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import User.User;

public class Arrays extends AppCompatActivity {

    private Integer[] imagesArrays = {R.drawable.arrays1start, R.drawable.arrays2declare, R.drawable.arrays3usage, R.drawable.arrays4twodimenssions, R.drawable.arrays5last};
    private ImageSwitcher imgSwArrays;
    private int currentImamge = -1;
    private Button nextSlide_arr;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrays);
        currentUser = (User)getIntent().getSerializableExtra("User");

        imgSwArrays = (ImageSwitcher) findViewById(R.id.is_picchanger_arr);
//

//        final ImageSwitcher imgSwitch = (ImageSwitcher) findViewById(R.id.is_picchanger);

        imgSwArrays.setFactory(new ViewSwitcher.ViewFactory(){
            public View makeView(){
                ImageView imgView = new ImageView(getApplicationContext());
                imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                imgSw.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imgView;
            }
        });

        nextSlide_arr = (Button) findViewById(R.id.btn_next_1_arrays);

        nextSlide_arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentImamge < imagesArrays.length - 1){
                    currentImamge++;
                    imgSwArrays.setImageResource(imagesArrays[currentImamge]);
                }

            }
        });

    }
}


