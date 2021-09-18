package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UserProfile extends AppCompatActivity {

    //button
    private EditText newName, newPass;
    private Button updateBtn;
    TextView userOuput, passOutput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        newName = findViewById(R.id.newUsername);
        newPass = findViewById(R.id.newPassword);
        updateBtn = findViewById(R.id.updBtn);

        userOuput = findViewById(R.id.showUser);
        String username = getIntent().getStringExtra("username");
        userOuput.setText(username);

        passOutput = findViewById(R.id.showPass);
        String password = getIntent().getStringExtra("password");
        passOutput.setText(password);


        //
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = newName.getText().toString();
                String passInput = newPass.getText().toString();

                if (userInput.isEmpty() && passInput.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Password and Username is empty", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.updateUser(userInput, passInput);
                            Intent intent = new Intent(UserProfile.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }).start();
                }

            }
        });
    }
}