package com.example.sethcohen.javaforeveryone3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class ConditionsAndLoops extends AppCompatActivity {
    private Integer[] imagesCondNLoops = {R.drawable.condnloops1conditoperatots, R.drawable.condnloops2ifelsestatement, R.drawable.condnloops3loops, R.drawable.condnloops4loopslife, R.drawable.condnloop5innerloop, R.drawable.condnloops6keywords};
    private ImageSwitcher imgSwCondNLoops;
    private int currentImamge = -1;
    private Button nextSlide_condnloops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions_and_loops);

        imgSwCondNLoops = (ImageSwitcher) findViewById(R.id.is_picchanger_condNloops);
//

//        final ImageSwitcher imgSwitch = (ImageSwitcher) findViewById(R.id.is_picchanger);

        imgSwCondNLoops.setFactory(new ViewSwitcher.ViewFactory(){
            public View makeView(){
                ImageView imgView = new ImageView(getApplicationContext());
                imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                imgSw.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return imgView;
            }
        });

        nextSlide_condnloops = (Button) findViewById(R.id.btn_next_1_condNloops);

        nextSlide_condnloops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentImamge < imagesCondNLoops.length - 1){
                    currentImamge++;
                    imgSwCondNLoops.setImageResource(imagesCondNLoops[currentImamge]);
                }

            }
        });

    }
}
