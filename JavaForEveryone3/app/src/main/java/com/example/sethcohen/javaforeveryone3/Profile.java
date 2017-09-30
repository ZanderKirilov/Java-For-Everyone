package com.example.sethcohen.javaforeveryone3;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import User.User;

public class Profile extends AppCompatActivity {
    private Button btnLogout;
    private Button btnHome;
    private Spinner spnOptions;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        currentUser = (User)getIntent().getSerializableExtra("User");

        TextView usernameET = (TextView)findViewById(R.id.username_profile);
        TextView emailET = (TextView)findViewById(R.id.email_insert_profile);
        TextView achievementET = (TextView)findViewById(R.id.lastAchievement_Input_Profile);
        TextView pointsET = (TextView)findViewById(R.id.Points_Input_Profile);
        TextView stageET = (TextView)findViewById(R.id.current_stage_input_profile);

        emailET.setText(currentUser.getE_mail());
        achievementET.setText(currentUser.getLast_achievement());
        pointsET.setText(currentUser.getPoints());
        stageET.setText(currentUser.getCurrent_stage());

        String username = currentUser.getUsername();
        usernameET.setText(username);

        logout(username);
        goHome(currentUser);
        chooseOption(username);

    }

    private void chooseOption(final String username) {
        spnOptions = (Spinner)findViewById(R.id.spn_options);
        final String[] items = new String[]{"Опции","Постижения", "Покани Приятел", "Смени Парола"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spnOptions.setAdapter(adapter);
        spnOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        Toast.makeText(Profile.this, username + " You choose " + items[i], Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(Profile.this, username + "You choose " + items[i], Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(Profile.this, username +"You choose " + items[i], Toast.LENGTH_SHORT).show();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void goHome(final User user) {
        btnHome = (Button)findViewById(R.id.btn_home_profile);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHome = new Intent(Profile.this, HomeScreen.class);
                goHome.putExtra("User", user);
                startActivity(goHome);
            }
        });
    }

    private void logout(final String user) {
        btnLogout = (Button)findViewById(R.id.btn_logout_prifle);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog outDial = new Dialog(Profile.this);
                outDial.setContentView(R.layout.dialog_logout);
                outDial.setTitle("Излез");
                Button btnYes = (Button)outDial.findViewById(R.id.btn_logout_Yes);
                Button btnNo = (Button)outDial.findViewById(R.id.btn_logoutt_NO);
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(Profile.this,"Довиждане, " + user,Toast.LENGTH_SHORT).show();
                        Intent goToMain = new Intent(Profile.this, MainActivity.class);
                        startActivity(goToMain);
                    }
                });
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        outDial.dismiss();
                    }
                });
                outDial.show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent goBack = new Intent(Profile.this, HomeScreen.class);
        goBack.putExtra("User", currentUser);
        startActivity(goBack);
    }
}
