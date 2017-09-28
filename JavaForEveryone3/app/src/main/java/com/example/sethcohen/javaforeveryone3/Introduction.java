package com.example.sethcohen.javaforeveryone3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Introduction extends AppCompatActivity {

    private Integer[] imagesIntro = {R.drawable.intro2fortheappandittalents, R.drawable.intro3thirdpage, R.drawable.intro4primitives, R.drawable.intro5primitivesecond, R.drawable.intro6moreonprimmitives, R.drawable.intro7moreonprimitives, R.drawable.intro8spesialsigns, R.drawable.intro9operations, R.drawable.intro10moreoperations, R.drawable.intro11scanner, R.drawable.intro12incrementdecrement, R.drawable.empty_pic};
    private ImageView imgSwIntro;
    private int currentImamge = 0;
    private Button nextSlide_intro;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        imgSwIntro = (ImageView) findViewById(R.id.is_picchanger_intro);


        nextSlide_intro = (Button) findViewById(R.id.btn_next_1_introduction);

        nextSlide_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSwIntro.setImageResource(imagesIntro[currentImamge]);
                currentImamge++;
                if (currentImamge == imagesIntro.length-1){
                    nextSlide_intro.setText("FINISH");
                }
                if (currentImamge >= imagesIntro.length) {
                    nextSlide_intro.setClickable(false);
                    final AlertDialog.Builder goToLoopsLog = new AlertDialog.Builder(Introduction.this);
                    goToLoopsLog.setTitle("ITTalents - JavaForEveryone");
                    goToLoopsLog.setMessage("\tDo you want to continue to \n Conditions and Loops");
                    goToLoopsLog.setIcon(R.drawable.it_talents_logo_inner);
                    goToLoopsLog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent goingToLoops = new Intent(Introduction.this, ConditionsAndLoops.class);
                            Toast.makeText(Introduction.this,"Proceeding to Conditions and Loops",Toast.LENGTH_SHORT).show();
                            startActivity(goingToLoops);
                        }
                    });
                    goToLoopsLog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent goingToLoops = new Intent(Introduction.this, HomeScreen.class);
                            startActivity(goingToLoops);
                        }
                    });
                    goToLoopsLog.setCancelable(false);
                    goToLoopsLog.show();
                }
            }
        });


    }
}
