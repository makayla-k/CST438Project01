package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserProfile extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText new_pass;
    private EditText new_user;
    private Button edit;
    private Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        username = findViewById(R.id.current_name);
        password = findViewById(R.id.current_pass);
        home = findViewById(R.id.home);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, LandingPage.class);
                startActivity(intent);
            }
        });
    }
}