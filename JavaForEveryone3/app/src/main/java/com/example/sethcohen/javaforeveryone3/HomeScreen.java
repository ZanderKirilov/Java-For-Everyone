package com.example.sethcohen.javaforeveryone3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        gotoIntroduction();
    }

    public void gotoIntroduction(){
        Button registerPage = (Button) findViewById(R.id.button1_Intro);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                            //Ако тук смениш Introduction.class със Register.class работи!
                Intent register = new Intent(HomeScreen.this, Introduction.class);
                startActivity(register);
            }
        });
    }


}
