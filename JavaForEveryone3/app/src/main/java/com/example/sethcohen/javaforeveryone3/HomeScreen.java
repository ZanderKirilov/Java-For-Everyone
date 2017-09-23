package com.example.sethcohen.javaforeveryone3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        gotoIntroduction();
        gotoConditionsAndLoops();
        gotoArrays();
        //showIntro();
    }

    public void gotoIntroduction(){
        Button registerPage = (Button) findViewById(R.id.button1_Intro);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent gotoIntro = new Intent(HomeScreen.this, Introduction.class);
                startActivity(gotoIntro);
            }
        });
    }





//    TextView textView;

//    public void showIntro(){
//        textView = (TextView) findViewById(R.id.text_Intro);
//        textView.setMovementMethod(new ScrollingMovementMethod());
//        String data = "";
//        StringBuffer sBuffer = new StringBuffer();
//        InputStream is = this.getResources().openRawResource(R.raw.intro);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        if(is != null){
//            try{
//                while ((data = reader.readLine()) != null){
//                    sBuffer.append(data + "n");
//                }
//                textView.setText(sBuffer);
//                is.close();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//    }

    public void gotoConditionsAndLoops(){
        Button registerPage = (Button) findViewById(R.id.button2_Loops);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent register = new Intent(HomeScreen.this, ConditionsAndLoops.class);
                startActivity(register);
            }
        });
    }

    public void gotoArrays(){
        Button registerPage = (Button) findViewById(R.id.button3_Arrays);
        registerPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent register = new Intent(HomeScreen.this, Arrays.class);
                startActivity(register);
            }
        });
    }


}
