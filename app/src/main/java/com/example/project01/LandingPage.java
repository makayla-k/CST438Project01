package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LandingPage extends AppCompatActivity {

    private TextView userDisplay;
    Button usrProfileBtn;

    private static final String TAG = LandingPage.class.getSimpleName();
    public static String BASE_URL = "https://rest.bandsintown.com/artists/";
    private static Retrofit retrofit = null;

    //    get app id from secrets file
    private String appId = BuildConfig.CONSUMER_KEY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        usrProfileBtn = findViewById(R.id.userProfile);
        userDisplay = findViewById(R.id.userDisplay);

        Log.d(TAG, "api" + BASE_URL + appId);

        connectAndGetApiData();

        // test for user sign in
        String userOutput = getIntent().getStringExtra("username");
        //abcuserDisplay.setText(userOutput);
        String passOutput = getIntent().getStringExtra("password");

        // User profile button
        usrProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                UserDao userDao = userDatabase.userDao();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UserEntity userEntity = userDao.getUser(userOutput);
                        if (userEntity == null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            // CALL API
                            // TEST
                            String username = userEntity.username;
                            String password = userEntity.password;
                            Intent intent = new Intent(LandingPage.this, UserProfile.class)
                                    .putExtra("username", username)
                                    .putExtra("password", password);
                            startActivity(intent);
                        }
                    }
                }).start();
            }
        });
    }
    private void connectAndGetApiData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        ArtistApiService artistApiService = retrofit.create(ArtistApiService.class);

        Call<Artist> call = artistApiService.getArtistInfo("drake", appId);

        call.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                Artist artist = response.body();

                Log.d(TAG, "Artist: " + artist.getName());
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }
}