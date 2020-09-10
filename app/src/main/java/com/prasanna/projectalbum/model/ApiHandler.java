package com.prasanna.projectalbum.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonArray;
import com.prasanna.projectalbum.view.MainActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class to handle the network calls for fetching the data from the API.
 */
public class ApiHandler {
    private static final String TAG = "ApiHandler";
    private static ApiHandler INSTANCE;
    private final MutableLiveData<List<AlbumData>> mListener;

    /**
     * Private constructor to implement Singleton class.
     *
     * @param albumDataListener LiveData to observe the album list changes.
     */
    private ApiHandler(MutableLiveData<List<AlbumData>> albumDataListener) {
        mListener = albumDataListener;
    }

    /**
     * Static method to get the instance of ApiHandler.
     *
     * @param albumDataListener LiveData to observe the album list changes.
     * @return ApiHandler instance.
     */
    public static ApiHandler getInstance(MutableLiveData<List<AlbumData>> albumDataListener) {
        if (INSTANCE == null) {
            INSTANCE = new ApiHandler(albumDataListener);
        }

        return INSTANCE;
    }

    /**
     * Method to fetch the Album list from the API.
     */
    public void getAlbumList() {
        new FetchAlbumsWorker().start();
    }

    /**
     * Class to fetch the album data in a background thread.
     */
    private static class FetchAlbumsWorker extends Thread {
        @Override
        public void run() {
            fetchApi(INSTANCE.mListener);
        }

        /**
         * Method to fetch the API data from the server.
         *
         * @param listener LiveData to observe the album list changes.
         */
        private void fetchApi(MutableLiveData<List<AlbumData>> listener) {
            ApiBuilder.getInstance().listAlbums().enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(@NotNull Call<JsonArray> call, @NotNull Response<JsonArray> response) {
                    JsonArray resultArray = response.body();
                    List<AlbumData> resultData = new ArrayList<>();
                    if (resultArray != null) {

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

                    MainActivity.mAlbums = new ArrayList<>(resultData);
                    listener.postValue(resultData);
                }

                @Override
                public void onFailure(@NotNull Call<JsonArray> call, @NotNull Throwable t) {
                    Log.e(TAG, "onError()" + t);
                }
            });
        }
    }
}
