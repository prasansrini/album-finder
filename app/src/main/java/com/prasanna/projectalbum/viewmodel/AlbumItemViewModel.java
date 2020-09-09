package com.prasanna.projectalbum.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.squareup.picasso.Picasso;

public class AlbumItemViewModel extends ViewModel {
    public MutableLiveData<String> mTitle = new MutableLiveData<>();
    public MutableLiveData<String> mImageUrl = new MutableLiveData<>();

    @BindingAdapter("imageurl")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(imageView);
        }
    }
}
