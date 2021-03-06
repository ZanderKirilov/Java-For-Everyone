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

import DBpack.LoginDataBaseAdapter;
import User.User;

public class Introduction extends AppCompatActivity {

    private Integer[] imagesIntro = {R.drawable.intro2fortheappandittalents, R.drawable.intro3thirdpage, R.drawable.intro4primitives, R.drawable.intro5primitivesecond, R.drawable.intro6moreonprimmitives, R.drawable.intro7moreonprimitives, R.drawable.intro8spesialsigns, R.drawable.intro9operations, R.drawable.intro10moreoperations, R.drawable.intro11scanner, R.drawable.intro12incrementdecrement, R.drawable.empty_pic};
    private ImageView imgSwIntro;
    private int currentImage = 0;
    private Button nextSlide_intro;
    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        logDBAdp=new LoginDataBaseAdapter(this);
        logDBAdp=logDBAdp.open();

        currentUser = (User)getIntent().getSerializableExtra("User");
        imgSwIntro = (ImageView) findViewById(R.id.is_picchanger_intro);


        nextSlide_intro = (Button) findViewById(R.id.btn_next_1_introduction);

        nextSlide_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSwIntro.setImageResource(imagesIntro[currentImage]);
                currentImage++;
                if (currentImage == imagesIntro.length-1){
                    nextSlide_intro.setText("КРАЙ");
                }
                if (currentImage >= imagesIntro.length) {
                    nextSlide_intro.setClickable(false);
                    final AlertDialog.Builder goToLoopsLog = new AlertDialog.Builder(Introduction.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                    goToLoopsLog.setTitle("ITTalents - JavaЗаВсеки");
                    goToLoopsLog.setMessage("\tИскате ли да продължите към \n Условия и Цикли?");
                    goToLoopsLog.setIcon(R.drawable.it_talents_logo_inner);
                    goToLoopsLog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent goingToLoops = new Intent(Introduction.this, ConditionsAndLoops.class);
                            Toast.makeText(Introduction.this,"Продължаваме към Условия и Цикли.",Toast.LENGTH_SHORT).show();
                            logDBAdp.updateUserStage(currentUser.getUsername(), "Условия и Цикли");
                            currentUser.setCurrent_stage("Условия и Цикли");
                            goingToLoops.putExtra("User", currentUser);
                            startActivity(goingToLoops);
                        }
                    });
                    goToLoopsLog.setNegativeButton("Не", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent goingToHome = new Intent(Introduction.this, HomeScreen.class);
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
        Intent goBack = new Intent(Introduction.this, HomeScreen.class);
        goBack.putExtra("User", currentUser);
        logDBAdp.close();
        finish();
        startActivity(goBack);
    }
}
