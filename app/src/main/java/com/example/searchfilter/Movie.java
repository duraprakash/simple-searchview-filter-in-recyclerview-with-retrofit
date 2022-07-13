package com.example.searchfilter;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("thumbnailUrl")
    private String thumbnailUrl;

    @SerializedName("image")
    private String url;

    public Movie(int ic_launcher_background, String one, String url) {
    }

    public Movie(String title, String thumbnailUrl, String url) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //    public Movie(String title, String url) {
//        this.title = title;
//        this.url = url;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String geturl() {
//        return url;
//    }
//
//    public void setThumbnailUrl(String url) {
//        this.url = url;
//    }
//
//    public void setImageUrl(String url) {
//        this.url = url;
//    }
}
