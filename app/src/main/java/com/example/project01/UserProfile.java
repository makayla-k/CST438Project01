package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class UserProfile extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText new_pass;
    private EditText new_user;
    private Button edit;
    private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }
}