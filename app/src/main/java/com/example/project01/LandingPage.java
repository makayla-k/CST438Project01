package com.example.project01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
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
    private RecyclerView recyclerView = null;


    //    get app id from secrets file
    private String appId = BuildConfig.CONSUMER_KEY;

//    Initialize list of artist
    List<String> artistNames;
    List<Artist> artistInfoList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        usrProfileBtn = findViewById(R.id.userProfile);
        userDisplay = findViewById(R.id.userDisplay);

        Log.d(TAG, "api" + BASE_URL + appId);

        artistNames = Arrays.asList("drake", "arianagrande", "giveon", "theweekend", "lilbaby", "nav");

        connectAndGetApiData(artistNames);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.setAdapter(new ArtistAdapter(artistInfoList, R.layout.list_item_artist, getApplicationContext()));
            }
        }, 3000);

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

    void connectAndGetApiData(List<String> artistNames) {

        List<Artist> tempArtistList = new ArrayList<>();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        ArtistApiService artistApiService = retrofit.create(ArtistApiService.class);

        for(String a : artistNames) {
            Call<Artist> call = artistApiService.getArtistInfo(a, appId);


            call.enqueue(new Callback<Artist>() {
                @Override
                public void onResponse(Call<Artist> call, Response<Artist> response) {
                    Artist artist = response.body();

                    Log.d(TAG, "Artist: " + artist.getName());
                    Log.d(TAG, "Event: " + artist.getUpcomingEventCount());

                    artistInfoList.add(artist);

                }

                @Override
                public void onFailure(Call<Artist> call, Throwable t) {
                    Log.e(TAG, t.toString());
                }
            });

        }

    }
}