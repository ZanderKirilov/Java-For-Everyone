package com.example.sethcohen.javaforeveryone3;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import DBpack.LoginDataBaseAdapter;
import User.User;

public class Profile extends AppCompatActivity {
    private Button btnLogout;
    private Button btnHome;
    private Spinner spnOptions;
    private User currentUser;
    private LoginDataBaseAdapter logDBAdp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        currentUser = (User)getIntent().getSerializableExtra("User");
        logDBAdp=new LoginDataBaseAdapter(this);
        logDBAdp=logDBAdp.open();

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
                        Intent goToAch = new Intent(Profile.this, Achievements.class);
                        goToAch.putExtra("User", currentUser);
                        startActivity(goToAch);
                        break;
                    case 2:
                        final Dialog invFriend = new Dialog(Profile.this);
                        invFriend.setContentView(R.layout.dialog_invite_friend);
                        invFriend.setTitle("Покани Приятел");
                        final EditText friendMail = (EditText)invFriend.findViewById(R.id.email_invite_friend);
                        Button inviteFriend = (Button)invFriend.findViewById(R.id.btn_invitefriend);
                        inviteFriend.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String friendEmail;
                                friendEmail = friendMail.getText().toString();
                                if (friendEmail.matches(Register.emailRegX)) {
                                    Toast.makeText(Profile.this, "На адрес : " + friendEmail + " беше изпратена покана.", Toast.LENGTH_LONG).show();
                                    invFriend.cancel();
                                }else{
                                    Toast.makeText(Profile.this, "Невалиден адрес, опитай отново...", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                        invFriend.show();
                        break;
                    case 3:
                        final Dialog chgPass = new Dialog(Profile.this);
                        chgPass.setContentView(R.layout.dialog_change_password);
                        chgPass.setTitle("Смени Парола");
                        final EditText newPass = (EditText)chgPass.findViewById(R.id.password_newpass);
                        final EditText newPass2 = (EditText)chgPass.findViewById(R.id.password_newpass2);
                        final EditText oldPass = (EditText)chgPass.findViewById(R.id.password_oldpass);
                        Button changePass = (Button)chgPass.findViewById(R.id.btn_changepass);
                        changePass.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String newPassword1,newPassword2,oldPassword, realOldPass;
                                newPassword1 = newPass.getText().toString();
                                newPassword2 = newPass2.getText().toString();
                                oldPassword = oldPass.getText().toString();
                                realOldPass = logDBAdp.getUserPassword(username);
                                if (!realOldPass.equals(oldPassword)){
                                    Toast.makeText(Profile.this, "Текущата парола е неправилна!", Toast.LENGTH_SHORT).show();
                                }else if (!newPassword1.equals(newPassword2)){
                                    Toast.makeText(Profile.this, "Новите пароли не съвпадат!", Toast.LENGTH_SHORT).show();
                                    newPass2.setError("Просто въведи : " + newPassword1);
                                }else{
                                    Toast.makeText(Profile.this, "Вашата парола е сменена!", Toast.LENGTH_SHORT).show();
                                    logDBAdp.updateEntry(username, newPassword1);
                                    chgPass.cancel();
                                }
                            }
                        });

                        chgPass.show();
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
        logDBAdp.close();
        startActivity(goBack);
    }
}
