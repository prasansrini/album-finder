package com.prasanna.projectalbum.model;

import com.prasanna.projectalbum.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {
    private ApiBuilder() {
    }

    public static AlbumApi getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(AlbumApi.class);
    }
}
