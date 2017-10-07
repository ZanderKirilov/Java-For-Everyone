package tests;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
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

public class AlgorithmsTEST extends AppCompatActivity {
    private RadioGroup radGrpFirstQ;
    private RadioGroup radGrpSecondQ;
    private RadioGroup radGrpThirdQ;
    private RadioGroup radGrpFourthQ;
    private String task1CorrectAnswer = "В зависимост от \nпромяна на вх.данни";
    private String task2CorrectAnswer = "O(NlogN)";
    private String task3CorrectAnswer = "Навсякъде";
    private String task4CorrectAnswer = "Константна";
    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;

    //За Пример Постижение ...
    private String allStagesCleared = "Влез в Матрицата!";
    private RelativeLayout holder;
    private TextView toastTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_algorithms);
        currentUser = (User) getIntent().getSerializableExtra("User");
        logDBAdp = new LoginDataBaseAdapter(this);
        logDBAdp = logDBAdp.open();

        //За Пример Постижение ...
        holder = (RelativeLayout) getLayoutInflater().inflate(R.layout.toast_achievement, (RelativeLayout) findViewById(R.id.rell_toast));
        toastTxt = (TextView) holder.findViewById(R.id.txt_toast);
        toastTxt.setText("Поздравления, ВИЕ отключихте постижение!\n" + "\n\"" + allStagesCleared + "\"");
        final Toast achieved = new Toast(AlgorithmsTEST.this);
        achieved.setGravity(Gravity.CENTER | Gravity.BOTTOM,0,0);
        achieved.setDuration(Toast.LENGTH_LONG);
        achieved.setView(holder);

        //За Sound Пример
        final MediaPlayer achievementUnlocked = MediaPlayer.create(AlgorithmsTEST.this, R.raw.achievement_unclocked);
        //
        //

        radGrpFirstQ = (RadioGroup)findViewById(R.id.rad_grp_first_algorithms);
        radGrpSecondQ = (RadioGroup)findViewById(R.id.rad_grp_second_algorithms);
        radGrpThirdQ = (RadioGroup)findViewById(R.id.rad_grp_third_algorithms);
        radGrpFourthQ = (RadioGroup)findViewById(R.id.rad_grp_fourth_algorithms);


        Button btn = (Button)findViewById(R.id.btn_submittest_algorithms);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RadioButton firstQ = (RadioButton)findViewById(radGrpFirstQ.getCheckedRadioButtonId());
                final RadioButton secondQ = (RadioButton)findViewById(radGrpSecondQ.getCheckedRadioButtonId());
                final RadioButton thirdQ = (RadioButton)findViewById(radGrpThirdQ.getCheckedRadioButtonId());
                final RadioButton fourthQ = (RadioButton)findViewById(radGrpFourthQ.getCheckedRadioButtonId());
                AlertDialog.Builder submit = new AlertDialog.Builder(AlgorithmsTEST.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                submit.setTitle("Предаване на Теста?");
                submit.setIcon(R.drawable.it_talents_logo_inner);
                submit.setMessage("Готови ли сте да предадете теста?");
                submit.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String firstAns = firstQ.getText().toString();
                        String secondAns = secondQ.getText().toString();
                        String thirdAns = thirdQ.getText().toString();
                        String fourthAns = fourthQ.getText().toString();
                        int score = 0;
                        if (firstAns.equalsIgnoreCase(task1CorrectAnswer)){
                            score += 25;
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
                            final int innerPoints = score;
                            if (currentUser.getCurrent_stage().equalsIgnoreCase("Матрицата")){

                                //За Пример Постижение ...
                                achieved.show();
                                currentUser.setLast_achievement(allStagesCleared);
                                currentUser.addPoints(10);

                                //За Sound Пример ...
                                achievementUnlocked.start();
                                //
                                //
                                AlertDialog.Builder congrats = new AlertDialog.Builder(AlgorithmsTEST.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                                congrats.setTitle("Ха, Отново!");
                                congrats.setMessage("\tВие отново преминахте теста за алгоритми... Точки : "+ innerPoints +" \n\tСега на къде?");
                                congrats.setIcon(R.drawable.it_talents_logo_inner);
                                congrats.setCancelable(false);
                                congrats.setPositiveButton("Към ITTalents", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent();
                                        intent.setAction(Intent.ACTION_VIEW);
                                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                        intent.setData(Uri.parse("http://www.ittalents.bg/home"));
                                        logDBAdp.updateUserStage(currentUser.getUsername(), "Матрицата");
                                        currentUser.setCurrent_stage("Матрицата");
                                        startActivity(intent);
                                    }
                                });
                                congrats.setNegativeButton("Назад", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent goingToLoops = new Intent(AlgorithmsTEST.this, HomeScreen.class);
                                        setResult(1, goingToLoops);
                                        logDBAdp.updateUserStage(currentUser.getUsername(), "Матрицата");
                                        currentUser.setCurrent_stage("Матрицата!");
                                        goingToLoops.putExtra("User", currentUser);
                                        startActivity(goingToLoops);
                                    }
                                });
                                congrats.show();
                            }
                            else{
                                //За Пример Постижение ...
                                achieved.show();
                                currentUser.setLast_achievement(allStagesCleared);
                                currentUser.addPoints(10);

                                //За Sound Пример ...
                                achievementUnlocked.start();
                                //
                                //

                                AlertDialog.Builder congrats = new AlertDialog.Builder(AlgorithmsTEST.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                                congrats.setTitle("Поздравления!");
                                congrats.setMessage("\tВие успешно преминахте всички етапи\n\tна JavaЗаВсеки!");
                                congrats.setIcon(R.drawable.it_talents_logo_inner);
                                congrats.setCancelable(false);
                                congrats.setPositiveButton("Към ITTalents", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent();
                                        intent.setAction(Intent.ACTION_VIEW);
                                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                                        intent.setData(Uri.parse("http://www.ittalents.bg/home"));
                                        logDBAdp.updateUserStage(currentUser.getUsername(), "Матрицата");
                                        currentUser.setCurrent_stage("Матрицата");
                                        startActivity(intent);
                                        }
                                    });
                                congrats.setNegativeButton("Назад", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent goingToLoops = new Intent(AlgorithmsTEST.this, HomeScreen.class);
                                        setResult(1, goingToLoops);
                                        logDBAdp.updateUserStage(currentUser.getUsername(), "Матрицата");
                                        currentUser.setCurrent_stage("Матрицата!");
                                        goingToLoops.putExtra("User", currentUser);
                                        startActivity(goingToLoops);
                                    }
                                });
                                congrats.show();
                            }
                        }else{
                            AlertDialog.Builder wrngTest = new AlertDialog.Builder(AlgorithmsTEST.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                            wrngTest.setTitle("Грешка!");
                            wrngTest.setIcon(R.drawable.it_talents_logo_inner);
                            wrngTest.setMessage("Точките ви не достигат - "+ score + " \nСега на къде?");
                            wrngTest.setPositiveButton("Към Етапи", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent goingToHome = new Intent(AlgorithmsTEST.this, HomeScreen.class);
                                    goingToHome.putExtra("User", currentUser);
                                    startActivity(goingToHome);
                                }
                            });
                            wrngTest.setNegativeButton("Опитай Отново", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent tryAgain = new Intent(AlgorithmsTEST.this, Algorithms.class);
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
