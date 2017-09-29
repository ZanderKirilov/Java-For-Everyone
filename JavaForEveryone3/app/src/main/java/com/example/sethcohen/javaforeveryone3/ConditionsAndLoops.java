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

import DBpack.LoginDataBaseAdapter;
import User.User;

public class ConditionsAndLoops extends AppCompatActivity {
    private Integer[] imagesCondNLoops = {R.drawable.condnloops2ifelsestatement, R.drawable.condnloops3loops, R.drawable.condnloops4loopslife, R.drawable.condnloop5innerloop, R.drawable.condnloops6keywords, R.drawable.empty_pic};
    private ImageView imgSwCondNLoops;
    private int currentImamge = 0;
    private Button nextSlide_condnloops;

    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions_and_loops);
        currentUser = (User)getIntent().getSerializableExtra("User");
        imgSwCondNLoops = (ImageView) findViewById(R.id.is_picchanger_condNloops);

        logDBAdp=new LoginDataBaseAdapter(this);
        logDBAdp=logDBAdp.open();

        nextSlide_condnloops = (Button) findViewById(R.id.btn_next_1_condNloops);

        nextSlide_condnloops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgSwCondNLoops.setImageResource(imagesCondNLoops[currentImamge]);
                currentImamge++;
                if (currentImamge == imagesCondNLoops.length-1){
                    nextSlide_condnloops.setText("FINISH");
                }
                if (currentImamge >= imagesCondNLoops.length) {
                    nextSlide_condnloops.setClickable(false);
                    final AlertDialog.Builder goToLoopsLog = new AlertDialog.Builder(ConditionsAndLoops.this);
                    goToLoopsLog.setTitle("ITTalents - JavaForEveryone");
                    goToLoopsLog.setMessage("\tDo you want to continue to \n Arrays");
                    goToLoopsLog.setIcon(R.drawable.it_talents_logo_inner);
                    goToLoopsLog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent goingToLoops = new Intent(ConditionsAndLoops.this, CondAndLoopsTEST.class);// Change Here!
                            Toast.makeText(ConditionsAndLoops.this,"Proceeding to Arrays",Toast.LENGTH_SHORT).show();
                            logDBAdp.updateUserStage(currentUser.getUsername(), "Arrays");
                            currentUser.setCurrent_stage("Arrays");
                            goingToLoops.putExtra("User", currentUser);
                            startActivity(goingToLoops);
                        }
                    });
                    goToLoopsLog.setNegativeButton("No", new DialogInterface.OnClickListener() {
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
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent goBack = new Intent(ConditionsAndLoops.this, HomeScreen.class);
        goBack.putExtra("User", currentUser);
        logDBAdp.close();
        finish();
        startActivity(goBack);
    }
}
