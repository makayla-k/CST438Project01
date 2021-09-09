package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class LandingPage extends AppCompatActivity {

    private TextView userDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        userDisplay = findViewById(R.id.userDisplay);

        String userOutput = getIntent().getStringExtra("username");
        userDisplay.setText(userOutput);
    }
}