/*
 * Account Created for testing purposes
 * Username: abc
 * Password: 123
 */

package com.example.project01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.username);
        pass = findViewById(R.id.password);

        loginBtn = findViewById(R.id.loginBtn);
        regBtn = findViewById(R.id.regBtn);

        // register button
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // login

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // doesnt work the pop up message aint doing its job
                String userField = name.getText().toString();
                String passField = pass.getText().toString();

                if (userField.isEmpty() && passField.isEmpty()) {
                    alert("No Password nor Username was entered");
                }
                // get user from database/do query
                else {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity userEntity = userDao.login(userField, passField);
                            if (userEntity == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // do error message
                                        Toast.makeText(getApplicationContext(), "does not match user", Toast.LENGTH_LONG).show();
                                    }
                                });
                            } else {
                                // match
                                String username = userEntity.username;
                                Intent intent = new Intent(MainActivity.this, LandingPage.class)
                                        .putExtra("username", username);
                                startActivity(intent);
                            }
                        }
                    }).start();
                }
            }
        });
    }
    private void alert(String message)
    {
        AlertDialog dlg = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("ok", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }})
                .create();
        dlg.show();
    }
}