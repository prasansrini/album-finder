package com.prasanna.projectalbum.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.prasanna.projectalbum.R;
import com.prasanna.projectalbum.databinding.ActivityMainBinding;
import com.prasanna.projectalbum.model.AlbumData;
import com.prasanna.projectalbum.viewmodel.AlbumListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        AlbumListViewModel viewModel = ViewModelProviders.of(this).get(AlbumListViewModel.class);
        MutableLiveData<List<AlbumData>> dataListener = new MutableLiveData<>();
        dataListener.observe(this, strings -> {
//                binding.albumRecycler.setAdapter();
        });


        viewModel.populateData(dataListener);

        binding.setViewModel(viewModel);
        initViews(binding);
    }

    private void initViews(ActivityMainBinding binding) {
        binding.albumRecycler.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        binding.albumRecycler.setLayoutManager(gridLayoutManager);
    }

    public interface AlbumDataListener {
        void onDataReceived();

        void onFailure();
    }
}
