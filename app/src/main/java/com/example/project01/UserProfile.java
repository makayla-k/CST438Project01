package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button edit;
    private Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        username = findViewById(R.id.current_name);
        password = findViewById(R.id.current_pass);
        home = findViewById(R.id.home);
        edit = findViewById(R.id.edit_profile);

       // String userData = userDao.getStringExtra(username);
        //String passData = UserDao.getStringExtra(password)
        // how do I recieve information to see if it displays on the screen or nah
        // do i use log in

        edit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        final UserEntity news = new UserEntity();
                                        news.setUsername(username.getText().toString());
                                        news.setPassword(password.getText().toString());
                                        UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                                        final UserDao newIdentiy = userDatabase.userDao();
                                        new Thread(() ->
                                        {
                                            // change name
                                            //not sure what to put here
                                         //   newIdentiy.registerUser(news);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Intent intent = new Intent(UserProfile.this, LandingPage.class);
                                                    startActivity(intent);
                                                }
                                            });
                                        }).start();
                                    }
                                });

                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UserProfile.this, LandingPage.class);
                        startActivity(intent);
                    }
                });
            }
}
