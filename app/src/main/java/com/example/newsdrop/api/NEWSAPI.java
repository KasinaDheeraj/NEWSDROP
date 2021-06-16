package com.example.newsdrop.api;

import com.example.newsdrop.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NEWSAPI {
    @GET("v2/top-headlines")
    Call<News> getHeadLines(@Query("country") String country,
                            @Query("apikey")String APIKEY);

    @GET("v2/top-headlines")
    Call<News> getCategorizedNews(@Query("country") String country,
                                @Query("category")String category,
                                @Query("apikey")String APIKEY);
    @GET("v2/everything")
    Call<News> getSearchNews(@Query("q")String query,
                             @Query("apikey")String APIKEY);

}
