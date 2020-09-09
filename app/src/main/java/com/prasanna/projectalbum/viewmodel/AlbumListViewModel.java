package com.prasanna.projectalbum.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prasanna.projectalbum.model.AlbumData;
import com.prasanna.projectalbum.model.ApiHandler;

import java.util.List;

public class AlbumListViewModel extends ViewModel {
    private LiveData<List<AlbumData>> mListener;
    private ApiHandler mApiHandler;

    public void populateData(MutableLiveData<List<AlbumData>> albumDataListener) {
        mListener = albumDataListener;
        mApiHandler = ApiHandler.getInstance(albumDataListener);
        mApiHandler.getAlbumList();
        // Fetch API
    }
}
