package tests;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sethcohen.javaforeveryone3.Arrays;
import com.example.sethcohen.javaforeveryone3.HomeScreen;
import com.example.sethcohen.javaforeveryone3.R;
import com.example.sethcohen.javaforeveryone3.Strings;

import DBpack.LoginDataBaseAdapter;
import User.User;

/**
 * Created by HP on 30.9.2017 г..
 */

public class MethodsAndRecursionTEST extends AppCompatActivity {
    private RadioGroup radGrpFirstQ;
    private RadioGroup radGrpSecondQ;
    private RadioGroup radGrpThirdQ;
    private RadioGroup radGrpFourthQ;

    private String task1CorrectAnswer = "Колкото Пожелаем";
    private String task2CorrectAnswer = "1";
    private String task3CorrectAnswer = "main";
    private String task4CorrectAnswer = "Да";
    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_methods);
        currentUser = (User) getIntent().getSerializableExtra("User");
        logDBAdp = new LoginDataBaseAdapter(this);
        logDBAdp = logDBAdp.open();

        radGrpFirstQ = (RadioGroup)findViewById(R.id.rad_grp_first_methods);
        radGrpSecondQ = (RadioGroup)findViewById(R.id.rad_grp_second_methods);
        radGrpThirdQ = (RadioGroup)findViewById(R.id.rad_grp_third_methods);
        radGrpFourthQ = (RadioGroup)findViewById(R.id.rad_grp_fourth_methods);
        Button btn = (Button)findViewById(R.id.btn_submittest_methods);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RadioButton rBtn = (RadioButton)findViewById(radGrpFirstQ.getCheckedRadioButtonId());
                final RadioButton rBtn2 = (RadioButton)findViewById(radGrpSecondQ.getCheckedRadioButtonId());
                final RadioButton rBtn3 = (RadioButton)findViewById(radGrpThirdQ.getCheckedRadioButtonId());
                final RadioButton rBtn4 = (RadioButton)findViewById(radGrpFourthQ.getCheckedRadioButtonId());
                AlertDialog.Builder submit = new AlertDialog.Builder(MethodsAndRecursionTEST.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                submit.setTitle("Предаване на Теста?");
                submit.setIcon(R.drawable.it_talents_logo_inner);
                submit.setMessage("Готови ли сте да предадете теста?");
                submit.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String firstAns = rBtn.getText().toString();
                        String secondAns = rBtn2.getText().toString();
                        String thirdAns = rBtn3.getText().toString();
                        String fourthAns = rBtn4.getText().toString();
                        int score = 0;
                        if (firstAns.equalsIgnoreCase(task1CorrectAnswer)){
                            score = 25;
                        }
                        if (secondAns.equalsIgnoreCase(task2CorrectAnswer)){
                            score += 25;
                        }
                        if (thirdAns.equalsIgnoreCase(task3CorrectAnswer)){
                            score += 25;
                        }
                        if (fourthAns.equalsIgnoreCase(task4CorrectAnswer)){
                            score += 25;
                        }

                        if (score >= 50){
                            Toast.makeText(MethodsAndRecursionTEST.this, "Поздравления, вие преминахте успешно! с " + score + " точки" ,Toast.LENGTH_SHORT).show();
                            Intent goingToLoops = new Intent(MethodsAndRecursionTEST.this, Strings.class);
                            logDBAdp.updateUserStage(currentUser.getUsername(), "Символни Низове");
                            currentUser.setCurrent_stage("Символни Низове");
                            goingToLoops.putExtra("User", currentUser);
                            startActivity(goingToLoops);
                        }else{
                            AlertDialog.Builder wrngTest = new AlertDialog.Builder(MethodsAndRecursionTEST.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                            wrngTest.setTitle("Грешка!");
                            wrngTest.setIcon(R.drawable.it_talents_logo_inner);
                            wrngTest.setMessage("Точките ви не достигат - "+ score + " \nСега на къде?");
                            wrngTest.setPositiveButton("Към Етапи", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent goingToHome = new Intent(MethodsAndRecursionTEST.this, HomeScreen.class);
                                    goingToHome.putExtra("User", currentUser);
                                    startActivity(goingToHome);
                                }
                            });
                            wrngTest.setNegativeButton("Опитай Отново", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent tryAgain = new Intent(MethodsAndRecursionTEST.this, Arrays.class);
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent goBack = new Intent(MethodsAndRecursionTEST.this, HomeScreen.class);
        goBack.putExtra("User", currentUser);
        logDBAdp.close();
        finish();
        startActivity(goBack);
    }
}