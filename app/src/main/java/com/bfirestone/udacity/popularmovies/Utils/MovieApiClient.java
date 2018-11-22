package com.bfirestone.udacity.popularmovies.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MovieApiClient {
    private static final String LOG_TAG = MovieApiClient.class.getSimpleName();


    public Retrofit getRetrofitClient(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }
}