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
import com.example.sethcohen.javaforeveryone3.MethodsAndRecursion;
import com.example.sethcohen.javaforeveryone3.R;

import DBpack.LoginDataBaseAdapter;
import User.User;

/**
 * Created by HP on 30.9.2017 г..
 */

public class ArraysTEST extends AppCompatActivity {
    private RadioGroup radGrpFirstQ;
    private RadioGroup radGrpSecondQ;
    private String task1CorrectAnswer = "6";
    private String task2CorrectAnswer = "Един";
    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_arrays);
        currentUser = (User) getIntent().getSerializableExtra("User");
        logDBAdp = new LoginDataBaseAdapter(this);
        logDBAdp = logDBAdp.open();

        radGrpFirstQ = (RadioGroup) findViewById(R.id.rad_grp_first_arrays);
        radGrpSecondQ = (RadioGroup) findViewById(R.id.rad_grp_second_arrays);
        Button btn = (Button)findViewById(R.id.btn_submittest_arrays);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RadioButton rBtn = (RadioButton)findViewById(radGrpFirstQ.getCheckedRadioButtonId());
                final RadioButton rBtn2 = (RadioButton)findViewById(radGrpSecondQ.getCheckedRadioButtonId());
                AlertDialog.Builder submit = new AlertDialog.Builder(ArraysTEST.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                submit.setTitle("Предаване на Теста?");
                submit.setIcon(R.drawable.it_talents_logo_inner);
                submit.setMessage("Готови ли сте да предадете теста?");
                submit.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (rBtn.getText().toString().equals(task1CorrectAnswer) && rBtn2.getText().toString().equals(task2CorrectAnswer)){
                            Toast.makeText(ArraysTEST.this, "Поздравления, вие преминахте успешно!" ,Toast.LENGTH_SHORT).show();
                            Intent goingToLoops = new Intent(ArraysTEST.this, MethodsAndRecursion.class);
                            logDBAdp.updateUserStage(currentUser.getUsername(), "Методи и Рекурсия");
                            currentUser.setCurrent_stage("Методи и Рекурсия");
                            goingToLoops.putExtra("User", currentUser);
                            startActivity(goingToLoops);
                        }else{
                            AlertDialog.Builder wrngTest = new AlertDialog.Builder(ArraysTEST.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                            wrngTest.setTitle("Грешка!");
                            wrngTest.setIcon(R.drawable.it_talents_logo_inner);
                            wrngTest.setMessage("Вашият тест не е правилен! \nСега на къде?");
                            wrngTest.setPositiveButton("Към Етапи", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent goingToHome = new Intent(ArraysTEST.this, HomeScreen.class);
                                    goingToHome.putExtra("User", currentUser);
                                    startActivity(goingToHome);
                                }
                            });
                            wrngTest.setNegativeButton("Опитай Отново", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent tryAgain = new Intent(ArraysTEST.this, Arrays.class);
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
        Intent goBack = new Intent(ArraysTEST.this, HomeScreen.class);
        goBack.putExtra("User", currentUser);
        logDBAdp.close();
        finish();
        startActivity(goBack);
    }
}