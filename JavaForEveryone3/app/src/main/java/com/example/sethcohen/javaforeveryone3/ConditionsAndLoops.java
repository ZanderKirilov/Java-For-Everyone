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

public class ConditionsAndLoops extends Stages {
    private Integer[] imagesCondNLoops = {R.drawable.condnloops2ifelsestatement, R.drawable.condnloops3loops, R.drawable.condnloops4loopslife, R.drawable.condnloop5innerloop, R.drawable.condnloops6keywords, R.drawable.empty_pic};
    private ImageView imgSwCondNLoops;
    private Button nextSlide_condnloops;

    private User currentUser;

    //Етапи - 1 Сегашен \n Предишен \n Следващ
    protected String className = "Условия и Цикли\nВъведение\nМасиви";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions_and_loops);
        currentUser = (User)getIntent().getSerializableExtra("User");
        imgSwCondNLoops = (ImageView) findViewById(R.id.is_picchanger_condNloops);

        nextSlide_condnloops = (Button) findViewById(R.id.btn_next_1_condNloops);

        switchPic(imgSwCondNLoops, imagesCondNLoops, nextSlide_condnloops, currentUser, ConditionsAndLoops.this, CondAndLoopsTEST.class);

    }

    @Override
    public String toString() {
        return this.className;
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
