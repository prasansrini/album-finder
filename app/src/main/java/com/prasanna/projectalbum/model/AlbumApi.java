package com.prasanna.projectalbum.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AlbumApi {
    @GET("prasansrini/album-data/albums")
    Call<JsonArray> listAlbums();
}
