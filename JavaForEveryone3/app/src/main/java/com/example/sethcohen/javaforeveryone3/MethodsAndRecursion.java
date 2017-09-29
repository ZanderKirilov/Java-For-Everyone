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


public class MethodsAndRecursion extends AppCompatActivity {
    private Integer[] imagesMethods = {R.drawable.metthods2, R.drawable.methods3declaration, R.drawable.method4return, R.drawable.methods5call, R.drawable.methods6main, R.drawable.methods7recursion1on1, R.drawable.methods8recursionwhy, R.drawable.methods9recursionelements, R.drawable.methods10recursionend, R.drawable.empty_pic};
    private ImageView imgMethods;
    private int currentImamge = 0;
    private Button nextSlide_methods;
    private Button prevSlide_methods;
    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_methods_and_recursion);
        currentUser = (User)getIntent().getSerializableExtra("User");
        logDBAdp=new LoginDataBaseAdapter(this);
        logDBAdp=logDBAdp.open();

        imgMethods = (ImageView) findViewById(R.id.is_picchanger_methods);
        nextSlide_methods = (Button) findViewById(R.id.btn_next_1_methods);

        nextSlide_methods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgMethods.setImageResource(imagesMethods[currentImamge]);
                currentImamge++;
                if (currentImamge == imagesMethods.length-1){
                    nextSlide_methods.setText("FINISH");
                }
                if (currentImamge >= imagesMethods.length) {
                    nextSlide_methods.setClickable(false);
                    final AlertDialog.Builder goToLoopsLog = new AlertDialog.Builder(MethodsAndRecursion.this);
                    goToLoopsLog.setTitle("ITTalents - JavaForEveryone");
                    goToLoopsLog.setMessage("\tDo you want to continue to \n Strings?");
                    goToLoopsLog.setIcon(R.drawable.it_talents_logo_inner);
                    goToLoopsLog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent goingToLoops = new Intent(MethodsAndRecursion.this, Strings.class);
                            Toast.makeText(MethodsAndRecursion.this,"Proceeding to Strings",Toast.LENGTH_SHORT).show();
                            logDBAdp.updateUserStage(currentUser.getUsername(), "Strings");
                            currentUser.setCurrent_stage("Strings");
                            goingToLoops.putExtra("User", currentUser);
                            startActivity(goingToLoops);
                        }
                    });
                    goToLoopsLog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent goingToHome = new Intent(MethodsAndRecursion.this, HomeScreen.class);
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
        Intent goBack = new Intent(MethodsAndRecursion.this, HomeScreen.class);
        goBack.putExtra("User", currentUser);
        logDBAdp.close();
        finish();
        startActivity(goBack);
    }
}