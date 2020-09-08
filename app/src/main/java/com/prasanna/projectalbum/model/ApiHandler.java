package com.prasanna.projectalbum.model;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiHandler {
    private static final String TAG = "ApiHandler";
    private static ApiHandler INSTANCE;
    private final LiveData<List<String>> mListener;

    private ApiHandler(LiveData<List<String>> albumDataListener) {
        mListener = albumDataListener;
    }

    public static ApiHandler getInstance(LiveData<List<String>> albumDataListener) {
        if (INSTANCE == null) {
            INSTANCE = new ApiHandler(albumDataListener);
        }

        return INSTANCE;
    }

    public void getAlbumList() {
        ApiBuilder.getInstance().listAlbums().enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.e(TAG, "Response: " + response.body().toString());
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e(TAG, "onError()" + t);
            }
        });
    }
}
