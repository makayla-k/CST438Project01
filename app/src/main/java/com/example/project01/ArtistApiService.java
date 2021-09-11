package com.example.project01;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArtistApiService {

    @GET("{artist}/")
    Call<Artist> getArtistInfo(@Path(value = "artist", encoded = true) String artist,@Query("app_id") String appId);
}
