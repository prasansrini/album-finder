package com.prasanna.projectalbum.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.prasanna.projectalbum.model.ApiHandler;

import java.util.List;

public class AlbumListViewModel extends ViewModel {
    private LiveData<List<String>> mListener;
    private ApiHandler mApiHandler;

    public void populateData(LiveData<List<String>> albumDataListener) {
        mListener = albumDataListener;
        mApiHandler = ApiHandler.getInstance(albumDataListener);
        mApiHandler.getAlbumList();
        // Fetch API
    }
}
