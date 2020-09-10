package com.prasanna.projectalbum.model;

import com.prasanna.projectalbum.util.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class to build the Retrofit instance. Singleton class to provide the instance of Retrofit.
 */
public class ApiBuilder {
    /**
     * Private constructor for Singleton implementation.
     */
    private ApiBuilder() {
    }

    /**
     * Static method to fetch the Singleton object of ApiBuilder.
     *
     * @return ApiBuilder instance.
     */
    public static AlbumApi getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(AlbumApi.class);
    }
}
