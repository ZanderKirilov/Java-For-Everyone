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

public class Arrays extends Stages {

    private Integer[] imagesArrays = { R.drawable.arrays2declare, R.drawable.arrays3usage, R.drawable.arrays4twodimenssions, R.drawable.arrays5last, R.drawable.empty_pic};
    private ImageView imgSwArrays;
    private Button nextSlide_arr;

    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;

    //Етапи - 1 Сегашен \n Предишен \n Следващ
    protected String className = "Масиви\nУсловия и Цикли\nМетоди и Рекурсия";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrays);
        currentUser = (User)getIntent().getSerializableExtra("User");
        logDBAdp=new LoginDataBaseAdapter(this);
        logDBAdp=logDBAdp.open();

        imgSwArrays = (ImageView) findViewById(R.id.is_picchanger_arr);

        nextSlide_arr = (Button) findViewById(R.id.btn_next_1_arrays);

        switchPic(imgSwArrays, imagesArrays, nextSlide_arr, currentUser, Arrays.this, ArraysTEST.class);

    }

    @Override
    public String toString() {
        return this.className;
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


