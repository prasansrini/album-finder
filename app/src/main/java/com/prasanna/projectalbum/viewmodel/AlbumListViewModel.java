package com.prasanna.projectalbum.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prasanna.projectalbum.model.AlbumData;
import com.prasanna.projectalbum.model.ApiHandler;
import com.prasanna.projectalbum.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class AlbumListViewModel extends ViewModel {
    private static final String TAG = "AlbumListViewModel";
    public final MutableLiveData<String> mSearchText = new MutableLiveData<>();
    private MutableLiveData<List<AlbumData>> mAlbumDataListener = new MutableLiveData<>();

    public void setBuilderData(MutableLiveData<List<AlbumData>> albumDataListener, LifecycleOwner lifeCycleOwner) {
        mAlbumDataListener = albumDataListener;

        mSearchText.observe(lifeCycleOwner, this::buildSearchedData);
    }

    private void buildSearchedData(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            mAlbumDataListener.setValue(MainActivity.mAlbums);
            return;
        }

        List<AlbumData> loadedList = MainActivity.mAlbums;
        List<AlbumData> resultAlbums = new ArrayList<>();

        if (loadedList != null) {
            for (AlbumData albumData : loadedList) {
                if (albumData.getTitle().contains(searchText)) {
                    resultAlbums.add(albumData);
                }
            }
        }

        mAlbumDataListener.setValue(resultAlbums);
    }

    public void populateData() {
        ApiHandler.getInstance(mAlbumDataListener).getAlbumList();
    }

    public void onRefreshClicked() {
        populateData();
    }
}
