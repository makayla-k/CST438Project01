package com.example.project01;

import android.util.Pair;

import com.google.gson.annotations.SerializedName;

public class Artist {

    @SerializedName("name")
    private String name;

    @SerializedName("image_url")
    private String imgageUrl;

    @SerializedName("upcoming_event_count")
    private int upcomingEventCount;

    public Artist(String name, String imgageUrl, int upcomingEventCount) {
        this.name = name;
        this.imgageUrl = imgageUrl;
        this.upcomingEventCount = upcomingEventCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgageUrl() {
        return imgageUrl;
    }

    public void setImgageUrl(String imgageUrl) {
        this.imgageUrl = imgageUrl;
    }

    public int getUpcomingEventCount() {
        return upcomingEventCount;
    }

    public void setUpcomingEventCount(int upcomingEventCount) {
        this.upcomingEventCount = upcomingEventCount;
    }
}
