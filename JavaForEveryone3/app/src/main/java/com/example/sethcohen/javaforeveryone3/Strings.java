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
import tests.MethodsAndRecursionTEST;
import tests.StringsTEST;

public class Strings extends AppCompatActivity {
    private Integer[] imagesStrings = {R.drawable.string2stackheap, R.drawable.string3stringpool, R.drawable.string4stringequality, R.drawable.string5stringandequals, R.drawable.string6methods, R.drawable.string7stringbuider, R.drawable.empty_pic};
    private ImageView imgStrings;
    private int currentImamge = 0;
    private Button nextSlide_strings;
    private Button prevSlide_strings;

    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strings);
        currentUser = (User)getIntent().getSerializableExtra("User");
        logDBAdp=new LoginDataBaseAdapter(this);
        logDBAdp=logDBAdp.open();

        imgStrings = (ImageView) findViewById(R.id.is_picchanger_strings);
        nextSlide_strings = (Button) findViewById(R.id.btn_next_1_strings);

        nextSlide_strings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgStrings.setImageResource(imagesStrings[currentImamge]);
                currentImamge++;
                if (currentImamge == imagesStrings.length-1){
                    nextSlide_strings.setText("КРАЙ");
                }
                if (currentImamge >= imagesStrings.length) {
                    nextSlide_strings.setClickable(false);
                    final AlertDialog.Builder goToLoopsLog = new AlertDialog.Builder(Strings.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                    goToLoopsLog.setTitle("ITTalents - JavaЗаВсеки");
                    goToLoopsLog.setMessage("\tЖелаете ли да отворите теста за \n Символни Низове?");
                    goToLoopsLog.setIcon(R.drawable.it_talents_logo_inner);
                    goToLoopsLog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent goingToLoops = new Intent(Strings.this, StringsTEST.class);
                            Toast.makeText(Strings.this,"Продължаваме към теста за Символни Низове...",Toast.LENGTH_SHORT).show();
                            goingToLoops.putExtra("User", currentUser);
                            startActivity(goingToLoops);
                        }
                    });
                    goToLoopsLog.setNegativeButton("Не", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent goingToHome = new Intent(Strings.this, HomeScreen.class);
                            goingToHome.putExtra("User", currentUser);
                            startActivity(goingToHome);
                        }
                    });
                    goToLoopsLog.setCancelable(false);
                    goToLoopsLog.show();
                }

            }
        });

        prevSlide_strings = (Button) findViewById(R.id.btn_prev_1_strings);

        prevSlide_strings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentImamge <= 0){
                    Toast.makeText(Strings.this, "Няма предишна страница",Toast.LENGTH_SHORT).show();
                    currentImamge = 0;
                }
                imgStrings.setImageResource(imagesStrings[currentImamge]);
                currentImamge--;
                if(currentImamge == imagesStrings.length){
                    currentImamge = 0;
                }
//                if (currentImamge <= 0){
//                    Toast.makeText(Strings.this, "There is no Previous page",Toast.LENGTH_SHORT).show();
//                    currentImamge = 0;
//                }

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent goBack = new Intent(Strings.this, HomeScreen.class);
        goBack.putExtra("User", currentUser);
        logDBAdp.close();
        finish();
        startActivity(goBack);
    }
}
