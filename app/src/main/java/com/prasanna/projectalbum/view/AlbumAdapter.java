package com.prasanna.projectalbum.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.prasanna.projectalbum.R;
import com.prasanna.projectalbum.databinding.AlbumItemBinding;
import com.prasanna.projectalbum.model.AlbumData;
import com.prasanna.projectalbum.viewmodel.AlbumItemViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {

    private static final String TAG = "AlbumAdapter";
    private final List<AlbumData> mAlbumDataList;

    public AlbumAdapter(List<AlbumData> albumDataList) {
        mAlbumDataList = new ArrayList<>(albumDataList);
    }

    @NonNull
    @NotNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        AlbumItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.album_item, parent, false);

        return new AlbumViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AlbumViewHolder holder, int position) {
        AlbumItemViewModel viewModel = new AlbumItemViewModel();
        viewModel.mTitle.setValue(mAlbumDataList.get(position).getTitle().replace("\"", ""));
        viewModel.mImageUrl.setValue(mAlbumDataList.get(position).getThumbnailUrl().replace("\"", ""));
        holder.bind(viewModel);
    }

    @Override
    public int getItemCount() {
        return mAlbumDataList.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        AlbumItemBinding mBinding;

        public AlbumViewHolder(@NonNull @NotNull AlbumItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(AlbumItemViewModel viewModel) {
            mBinding.setViewModel(viewModel);
            mBinding.executePendingBindings();
        }
    }
}
