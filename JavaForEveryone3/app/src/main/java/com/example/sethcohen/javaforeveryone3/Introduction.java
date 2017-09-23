package com.example.sethcohen.javaforeveryone3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Introduction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
    }

//    TextView textView;
//
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
}
