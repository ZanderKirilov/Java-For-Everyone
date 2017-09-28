package com.example.sethcohen.javaforeveryone3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class Tester2 extends AppCompatActivity {
    int imageNum[] = {R.drawable.intro1, R.drawable.intro2, R.drawable.intro3, R.drawable.intro4, R.drawable.intro5, R.drawable.intro6, R.drawable.intro7};

    int countImg = imageNum.length;
    int currentImamge = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester2);

        Button nextSlide = (Button) findViewById(R.id.btn_next_slide);
        final ImageSwitcher imgSwitch = (ImageSwitcher) findViewById(R.id.is_picchanger);

        imgSwitch.setFactory(new ViewSwitcher.ViewFactory(){
            public View makeView(){
                ImageView imgView = new ImageView(getApplicationContext());
                imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imgSwitch.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imgView;
            }
        });

        nextSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentImamge++;
                if(currentImamge == countImg){
                    countImg = 0;
                }

                imgSwitch.setImageResource(imageNum[currentImamge]);
            }
        });

    }
}
