package com.example.searchfilter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiset {
    // 2.0 create api interface (API INTERFACE [2])
    // and add retrofit dependencies and glide for images (BUILD.GRADLE:MODULE)
    // then internet permission (MANIFEST)

//    TODO: 2. Change json_user_fetch.php and responsemodel

    // 2.1 methods GET, POST, UPDATE, DELETE (METHODS)
    @GET("posts")
    // call list type data from responsemodel and method name
    Call<List<Movie>> getdata();

    // 2.2 go to apicontroller class
}
