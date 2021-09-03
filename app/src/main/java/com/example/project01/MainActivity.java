package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //buttons
    private EditText name, pass;
    private Button loginBtn, regBtn;

    private String test1 = "test1", test2 = "test2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        regBtn = findViewById(R.id.regBtn);

        // register data in room first
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // doesnt work the pop up message aint doing its job
                String userField = name.getText().toString();
                String passField = pass.getText().toString();
                if(userField.equals(test1) && passField.equals(test2))
                {
                    //landing page doesnt exist yet but the login should work
                    //did a test user and pass
                    Intent intent = new Intent(MainActivity.this, LandingPage.class);
                    startActivity(intent);
                }
                if(userField.isEmpty() && passField.isEmpty())
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Password and Username is empty", Toast.LENGTH_LONG);
                    toast.show();
                }
                //makayla landpage
              //  Intent intent = new Intent(MainActivity.this, LandingPage.class);

            }
        });

    }

}