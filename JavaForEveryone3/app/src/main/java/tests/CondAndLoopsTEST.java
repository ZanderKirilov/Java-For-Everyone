package tests;

import android.content.Intent;
import android.os.Bundle;
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
                RadioButton rBtn = (RadioButton)findViewById(radGrpFirstQ.getCheckedRadioButtonId());
                RadioButton rBtn2 = (RadioButton)findViewById(radGrpSecondQ.getCheckedRadioButtonId());

                if (rBtn.getText().toString().equals(task1CorrectAnswer) && rBtn2.getText().toString().equals(task2CorrectAnswer)){
                    Toast.makeText(CondAndLoopsTEST.this, "You are correct! Congratulations" ,Toast.LENGTH_SHORT).show();
                    Intent goingToLoops = new Intent(CondAndLoopsTEST.this, Arrays.class);
                    logDBAdp.updateUserStage(currentUser.getUsername(), "Arrays");
                    currentUser.setCurrent_stage("Arrays");
                    goingToLoops.putExtra("User", currentUser);
                    startActivity(goingToLoops);
                }else{
                    Toast.makeText(CondAndLoopsTEST.this,"There is something wrong, try again...",Toast.LENGTH_SHORT).show();
                    Intent goingToHome = new Intent(CondAndLoopsTEST.this, ConditionsAndLoops.class);
                    goingToHome.putExtra("User", currentUser);
                    startActivity(goingToHome);
                }
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