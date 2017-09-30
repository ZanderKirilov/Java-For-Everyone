package tests;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sethcohen.javaforeveryone3.Algorithms;
import com.example.sethcohen.javaforeveryone3.HomeScreen;
import com.example.sethcohen.javaforeveryone3.R;
import com.example.sethcohen.javaforeveryone3.Strings;

import DBpack.LoginDataBaseAdapter;
import User.User;

/**
 * Created by HP on 30.9.2017 г..
 */

public class StringsTEST extends AppCompatActivity {
    private EditText firstQ;
    private EditText secondQ;
    private RadioGroup radGrpThirdQ;
    private String task1and2CorrectAnswer = "false";
    private String task3CorrectAnswer = "null";
    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_strings);
        currentUser = (User) getIntent().getSerializableExtra("User");
        logDBAdp = new LoginDataBaseAdapter(this);
        logDBAdp = logDBAdp.open();

        firstQ = (EditText)findViewById(R.id.edtxt_1_ans);
        secondQ = (EditText)findViewById(R.id.edtxt_2_ans);
        radGrpThirdQ = (RadioGroup)findViewById(R.id.rad_grp_third_strings);

        Button btn = (Button)findViewById(R.id.btn_submittest_strings);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder submit = new AlertDialog.Builder(StringsTEST.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                submit.setTitle("Предаване на Теста?");
                submit.setIcon(R.drawable.it_talents_logo_inner);
                submit.setMessage("Готови ли сте да предадете теста?");
                submit.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int score = 0;
                        String firstAnswer = "";
                        String secondAnswer = "";
                        if (firstQ != null){
                            firstAnswer = firstQ.getText().toString();
                        }
                        if (secondQ != null){
                            secondAnswer = secondQ.getText().toString();
                        }
                        RadioButton rbtnAnswer3 = (RadioButton)findViewById(radGrpThirdQ.getCheckedRadioButtonId());
                        if (firstAnswer.equalsIgnoreCase(task1and2CorrectAnswer)){
                            score += 33;
                        }
                        if (secondAnswer.equalsIgnoreCase(task1and2CorrectAnswer)){
                            score += 33;
                        }
                        if (rbtnAnswer3.getText().toString().equalsIgnoreCase(task3CorrectAnswer)){
                            score += 33;
                        }
                        if (score == 99){
                            score = 100;
                        }
                        if (score >= 50){
                            Toast.makeText(StringsTEST.this, "Поздравления, вие преминахте успешно! с " + score + " точки" ,Toast.LENGTH_SHORT).show();
                            Intent goingToLoops = new Intent(StringsTEST.this, Algorithms.class);
                            logDBAdp.updateUserStage(currentUser.getUsername(), "Алгоритми");
                            currentUser.setCurrent_stage("Алгоритми");
                            goingToLoops.putExtra("User", currentUser);
                            startActivity(goingToLoops);
                        }else {
                            AlertDialog.Builder wrngTest = new AlertDialog.Builder(StringsTEST.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                            wrngTest.setTitle("Грешка!");
                            wrngTest.setIcon(R.drawable.it_talents_logo_inner);
                            wrngTest.setMessage("Точките ви не достигат - " + score + " \nСега на къде?");
                            wrngTest.setPositiveButton("Към Етапи", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent goingToHome = new Intent(StringsTEST.this, HomeScreen.class);
                                    goingToHome.putExtra("User", currentUser);
                                    startActivity(goingToHome);
                                }
                            });
                            wrngTest.setNegativeButton("Опитай Отново", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent tryAgain = new Intent(StringsTEST.this, Strings.class);
                                    tryAgain.putExtra("User", currentUser);
                                    startActivity(tryAgain);
                                }
                            });
                            wrngTest.show();
                        }
                    }
                });
                submit.setNegativeButton("Не", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                submit.show();
            }
        });

    }
}