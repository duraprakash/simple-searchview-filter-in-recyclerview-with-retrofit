package com.example.searchfilter;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String url;

    public Movie() {
    }

    public Movie(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String geturl() {
        return url;
    }

    public void setThumbnailUrl(String url) {
        this.url = url;
    }

    public void setImageUrl(String url) {
        this.url = url;
    }
}
