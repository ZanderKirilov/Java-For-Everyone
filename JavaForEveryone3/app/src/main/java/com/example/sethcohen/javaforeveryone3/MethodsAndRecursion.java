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


public class MethodsAndRecursion extends Stages {
    private Integer[] imagesMethods = {R.drawable.metthods2, R.drawable.methods3declaration, R.drawable.method4return, R.drawable.methods5call, R.drawable.methods6main, R.drawable.methods7recursion1on1, R.drawable.methods8recursionwhy, R.drawable.methods9recursionelements, R.drawable.methods10recursionend, R.drawable.empty_pic};
    private ImageView imgMethods;
    private Button nextSlide_methods;
    private Button prevSlide_methods;
    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;
                                //Етапи - 1 Сегашен \n Предишен \n Следващ
    protected String className = "Методи и Рекурсия\nМасиви\nСимволни Низове";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_methods_and_recursion);
        currentUser = (User)getIntent().getSerializableExtra("User");
        logDBAdp=new LoginDataBaseAdapter(this);
        logDBAdp=logDBAdp.open();

        imgMethods = (ImageView) findViewById(R.id.is_picchanger_methods);
        nextSlide_methods = (Button) findViewById(R.id.btn_next_1_methods);
        prevSlide_methods = (Button) findViewById(R.id.btn_prev_1_methods);

        Switcher(imgMethods, imagesMethods, nextSlide_methods,prevSlide_methods, currentUser, MethodsAndRecursion.this, MethodsAndRecursionTEST.class);




    }

    @Override
    public String toString() {
        return this.className;
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
