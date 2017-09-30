package com.example.sethcohen.javaforeveryone3;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import DBpack.LoginDataBaseAdapter;
import User.User;
import tests.ArraysTEST;

public class Arrays extends AppCompatActivity {

    private Integer[] imagesArrays = { R.drawable.arrays2declare, R.drawable.arrays3usage, R.drawable.arrays4twodimenssions, R.drawable.arrays5last, R.drawable.empty_pic};
    private ImageView imgSwArrays;
    private int currentImamge = 0;
    private Button nextSlide_arr;

    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrays);
        currentUser = (User)getIntent().getSerializableExtra("User");
        logDBAdp=new LoginDataBaseAdapter(this);
        logDBAdp=logDBAdp.open();

        imgSwArrays = (ImageView) findViewById(R.id.is_picchanger_arr);

        nextSlide_arr = (Button) findViewById(R.id.btn_next_1_arrays);

        nextSlide_arr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSwArrays.setImageResource(imagesArrays[currentImamge]);
                currentImamge++;
                if (currentImamge == imagesArrays.length-1){
                    nextSlide_arr.setText("КРАЙ");
                }
                if (currentImamge >= imagesArrays.length) {
                    nextSlide_arr.setClickable(false);
                    final AlertDialog.Builder goToLoopsLog = new AlertDialog.Builder(Arrays.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                    goToLoopsLog.setTitle("ITTalents - JavaЗаВсеки");
                    goToLoopsLog.setMessage("\tЖелаете ли да отворите теста за \n Масиви?");
                    goToLoopsLog.setIcon(R.drawable.it_talents_logo_inner);
                    goToLoopsLog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent goingToLoops = new Intent(Arrays.this, ArraysTEST.class);
                            Toast.makeText(Arrays.this,"Продължаваме към теста за Масиви...",Toast.LENGTH_SHORT).show();
                            goingToLoops.putExtra("User", currentUser);
                            startActivity(goingToLoops);
                        }
                    });
                    goToLoopsLog.setNegativeButton("Не", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent goingToHome = new Intent(Arrays.this, HomeScreen.class);
                            goingToHome.putExtra("User", currentUser);
                            startActivity(goingToHome);
                        }
                    });
                    goToLoopsLog.setCancelable(false);
                    goToLoopsLog.show();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent goBack = new Intent(Arrays.this, HomeScreen.class);
        goBack.putExtra("User", currentUser);
        logDBAdp.close();
        finish();
        startActivity(goBack);
    }
}


