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
import tests.CondAndLoopsTEST;

import User.User;

public class ConditionsAndLoops extends AppCompatActivity {
    private Integer[] imagesCondNLoops = {R.drawable.condnloops2ifelsestatement, R.drawable.condnloops3loops, R.drawable.condnloops4loopslife, R.drawable.condnloop5innerloop, R.drawable.condnloops6keywords, R.drawable.empty_pic};
    private ImageView imgSwCondNLoops;
    private int currentImamge = 0;
    private Button nextSlide_condnloops;

    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions_and_loops);
        currentUser = (User)getIntent().getSerializableExtra("User");
        imgSwCondNLoops = (ImageView) findViewById(R.id.is_picchanger_condNloops);

        nextSlide_condnloops = (Button) findViewById(R.id.btn_next_1_condNloops);

        nextSlide_condnloops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSwCondNLoops.setImageResource(imagesCondNLoops[currentImamge]);
                currentImamge++;
                if (currentImamge == imagesCondNLoops.length-1){
                    nextSlide_condnloops.setText("КРАЙ");
                }
                if (currentImamge >= imagesCondNLoops.length) {
                    nextSlide_condnloops.setClickable(false);
                    if (!currentUser.getCurrent_stage().equalsIgnoreCase("Условия и Цикли")){
                        final AlertDialog.Builder testDone = new AlertDialog.Builder(ConditionsAndLoops.this,android.R.style.Theme_Holo_Dialog_MinWidth);
                        testDone.setTitle("ITTalents - JavaЗаВсеки");
                        testDone.setMessage("\tВие успешно сте преминали теста за \n Условия и Цикли,\n искате ли да го посетите отново?");
                        testDone.setIcon(R.drawable.it_talents_logo_inner);
                        testDone.setCancelable(false);
                        testDone.setPositiveButton("Да, Към теста!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent goingToLoops = new Intent(ConditionsAndLoops.this, CondAndLoopsTEST.class);// Change Here!
                                Toast.makeText(ConditionsAndLoops.this,"Продължаваме към теста за Условия и Цикли...",Toast.LENGTH_SHORT).show();
                                goingToLoops.putExtra("User", currentUser);
                                startActivity(goingToLoops);
                            }
                        });
                        testDone.setNegativeButton("Не, Към Етапи!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent goingToHome = new Intent(ConditionsAndLoops.this, HomeScreen.class);
                                goingToHome.putExtra("User", currentUser);
                                startActivity(goingToHome);
                            }
                        });
                        testDone.setNeutralButton("Не, Към Масиви!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent goingToLoops = new Intent(ConditionsAndLoops.this, Arrays.class);// Change Here!
                                Toast.makeText(ConditionsAndLoops.this,"Продължаваме към Масиви...",Toast.LENGTH_SHORT).show();
                                goingToLoops.putExtra("User", currentUser);
                                startActivity(goingToLoops);
                            }
                        });
                        testDone.show();
                    }
                    else {
                        final AlertDialog.Builder goToLoopsLog = new AlertDialog.Builder(ConditionsAndLoops.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                        goToLoopsLog.setTitle("ITTalents - JavaЗаВсеки");
                        goToLoopsLog.setMessage("\tЖелаете ли да отворите теста за \n Условия и Цикли?");
                        goToLoopsLog.setIcon(R.drawable.it_talents_logo_inner);
                        goToLoopsLog.setCancelable(false);
                        goToLoopsLog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent goingToLoops = new Intent(ConditionsAndLoops.this, CondAndLoopsTEST.class);// Change Here!
                                Toast.makeText(ConditionsAndLoops.this, "Продължаваме към теста за Условия и Цикли...", Toast.LENGTH_SHORT).show();
                                goingToLoops.putExtra("User", currentUser);
                                startActivity(goingToLoops);
                            }
                        });
                        goToLoopsLog.setNegativeButton("Не", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent goingToHome = new Intent(ConditionsAndLoops.this, HomeScreen.class);
                                goingToHome.putExtra("User", currentUser);
                                startActivity(goingToHome);
                            }
                        });
                        goToLoopsLog.setCancelable(false);
                        goToLoopsLog.show();
                    }
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent goBack = new Intent(ConditionsAndLoops.this, HomeScreen.class);
        goBack.putExtra("User", currentUser);
        finish();
        startActivity(goBack);
    }
}
