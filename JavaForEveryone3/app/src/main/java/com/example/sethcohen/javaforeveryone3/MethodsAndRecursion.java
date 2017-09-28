package com.example.sethcohen.javaforeveryone3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import User.User;


public class MethodsAndRecursion extends AppCompatActivity {
    private Integer[] imagesMethods = {R.drawable.metthods2, R.drawable.methods3declaration, R.drawable.method4return, R.drawable.methods5call, R.drawable.methods6main, R.drawable.methods7recursion1on1, R.drawable.methods8recursionwhy, R.drawable.methods9recursionelements, R.drawable.methods10recursionend};
    private ImageView imgMethods;
    private int currentImamge = 0;
    private Button nextSlide_methods;
    private Button prevSlide_methods;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_methods_and_recursion);
        currentUser = (User)getIntent().getSerializableExtra("User");

        imgMethods = (ImageView) findViewById(R.id.is_picchanger_methods);
        nextSlide_methods = (Button) findViewById(R.id.btn_next_1_methods);

        nextSlide_methods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgMethods.setImageResource(imagesMethods[currentImamge]);
                currentImamge++;
                if(currentImamge == imagesMethods.length){
                    currentImamge = 0;
                }

            }
        });

        prevSlide_methods = (Button) findViewById(R.id.btn_prev_1_methods);

        prevSlide_methods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgMethods.setImageResource(imagesMethods[currentImamge]);
                currentImamge--;
                if(currentImamge == imagesMethods.length){
                    currentImamge = 0;
                }

            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent goBack = new Intent(MethodsAndRecursion.this, HomeScreen.class);
        goBack.putExtra("User", currentUser);
        startActivity(goBack);
    }
}
