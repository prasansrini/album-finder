package com.prasanna.projectalbum.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiHandler {
    private static final String TAG = "ApiHandler";
    private static ApiHandler INSTANCE;
    private final MutableLiveData<List<AlbumData>> mListener;

    private ApiHandler(MutableLiveData<List<AlbumData>> albumDataListener) {
        mListener = albumDataListener;
    }

    public static ApiHandler getInstance(MutableLiveData<List<AlbumData>> albumDataListener) {
        if (INSTANCE == null) {
            INSTANCE = new ApiHandler(albumDataListener);
        }

        return INSTANCE;
    }

    public void getAlbumList() {
        ApiBuilder.getInstance().listAlbums().enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray resultArray = response.body();
                List<AlbumData> resultData = new ArrayList<>();
                if (resultArray != null) {
//                    Log.e(TAG, "Response: " + resultArray.toString());

                    for (int element = 0; element < resultArray.size(); element++) {
                        AlbumData albumData = new AlbumData();
                        String title = resultArray.get(element).getAsJsonObject().get("title").toString();
                        String url = resultArray.get(element).getAsJsonObject().get("url").toString();
                        String thumbnailUrl = resultArray.get(element).getAsJsonObject().get("thumbnailUrl").toString();

                        albumData.setTitle(title);
                        albumData.setUrl(url);
                        albumData.setThumbnailUrl(thumbnailUrl);

                        resultData.add(albumData);
                    }
                } else {
                    Log.e(TAG, "Response NULL");
                }
                mListener.setValue(resultData);
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e(TAG, "onError()" + t);
            }
        });
    }
}
