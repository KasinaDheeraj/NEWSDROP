package com.example.newsdrop.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String BASE_URL="https://newsapi.org/";
    public static final String API_KEY="820db29518754234aad1b93357b6c2bf";
    // "43e31770ee6f403f82bf87afa9c5ec4d";


    public static NEWSAPI getClient(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NEWSAPI api=retrofit.create(NEWSAPI.class);
        return api;
    }
}
