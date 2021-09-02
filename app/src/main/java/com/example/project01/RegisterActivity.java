package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password;
    Button registerBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registerBtn = findViewById(R.id.regBtn);

        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // create user entity
                final UserEntity userEntity = new UserEntity();

                userEntity.setUsername(username.getText().toString());
                userEntity.setPassword(password.getText().toString());

                if(validateReg(userEntity)){
                    // insert
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run(){
                            // register
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "User Registered Success", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    }).start();
                    }else{
                    Toast.makeText(getApplicationContext(), "Error, must fill input boxes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateReg(UserEntity userEntity){
        if(userEntity.getUsername().isEmpty() || userEntity.getPassword().isEmpty() || userEntity.getUsername().isEmpty()){
            return false;
        }
        return true;
    }
}