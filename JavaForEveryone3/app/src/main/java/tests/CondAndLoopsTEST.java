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
import com.example.sethcohen.javaforeveryone3.ConditionsAndLoops;
import com.example.sethcohen.javaforeveryone3.HomeScreen;
import com.example.sethcohen.javaforeveryone3.R;

import DBpack.LoginDataBaseAdapter;
import User.User;

/**
 * Created by HP on 29.9.2017 г..
 */

public class CondAndLoopsTEST extends AppCompatActivity {

    private RadioGroup radGrpFirstQ;
    private RadioGroup radGrpSecondQ;
    private String task1CorrectAnswer = "2";
    private String task2CorrectAnswer = "Не би се компилирал";
    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        currentUser = (User)getIntent().getSerializableExtra("User");
        logDBAdp=new LoginDataBaseAdapter(this);
        logDBAdp=logDBAdp.open();

        radGrpFirstQ = (RadioGroup) findViewById(R.id.rad_grp_first_loops);
        radGrpSecondQ = (RadioGroup) findViewById(R.id.rad_grp_second_loops);
        Button btn = (Button)findViewById(R.id.btn_submittest_condloops);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final RadioButton rBtn = (RadioButton)findViewById(radGrpFirstQ.getCheckedRadioButtonId());
                final RadioButton rBtn2 = (RadioButton)findViewById(radGrpSecondQ.getCheckedRadioButtonId());
                AlertDialog.Builder submit = new AlertDialog.Builder(CondAndLoopsTEST.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                submit.setTitle("Предаване на Теста?");
                submit.setIcon(R.drawable.it_talents_logo_inner);
                submit.setMessage("Готови ли сте да предадете теста?");
                submit.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (rBtn.getText().toString().equals(task1CorrectAnswer) && rBtn2.getText().toString().equals(task2CorrectAnswer)){
                            Toast.makeText(CondAndLoopsTEST.this, "You are correct! Congratulations" ,Toast.LENGTH_SHORT).show();
                            Intent goingToLoops = new Intent(CondAndLoopsTEST.this, Arrays.class);
                            logDBAdp.updateUserStage(currentUser.getUsername(), "Arrays");
                            currentUser.setCurrent_stage("Arrays");
                            goingToLoops.putExtra("User", currentUser);
                            startActivity(goingToLoops);
                        }else{
                            AlertDialog.Builder wrngTest = new AlertDialog.Builder(CondAndLoopsTEST.this, android.R.style.Theme_Holo_Dialog_MinWidth);
                            wrngTest.setTitle("Грешка!");
                            wrngTest.setIcon(R.drawable.it_talents_logo_inner);
                            wrngTest.setMessage("Вашият тест не е правилен! \nСега на къде?");
                            wrngTest.setPositiveButton("Към Етапи", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent goingToHome = new Intent(CondAndLoopsTEST.this, HomeScreen.class);
                                    goingToHome.putExtra("User", currentUser);
                                    startActivity(goingToHome);
                                }
                            });
                            wrngTest.setNegativeButton("Опитай Отново", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent tryAgain = new Intent(CondAndLoopsTEST.this, ConditionsAndLoops.class);
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
        Intent goBack = new Intent(CondAndLoopsTEST.this, HomeScreen.class);
        goBack.putExtra("User", currentUser);
        logDBAdp.close();
        finish();
        startActivity(goBack);
    }
}
