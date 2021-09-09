package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LandingPage extends AppCompatActivity {

    private TextView userDisplay;
    Button usrProfileBtn;

//    get app id from secrets file
    private String appID = getString(R.string.app_id);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        usrProfileBtn = findViewById(R.id.userProfile);
        userDisplay = findViewById(R.id.userDisplay);

        // test for user sign in
        String userOutput = getIntent().getStringExtra("username");
        userDisplay.setText(userOutput);

        // User profile button
        usrProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LandingPage.this, UserProfile.class);
                startActivity(intent);
            }
        });
    }
}