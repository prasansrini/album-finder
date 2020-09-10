package com.prasanna.projectalbum.viewmodel;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.squareup.picasso.Picasso;

/**
 * Class to hold item data for the AlbumItem.
 */
public class AlbumItemViewModel extends ViewModel {
    public MutableLiveData<String> mTitle = new MutableLiveData<>();
    public MutableLiveData<String> mImageUrl = new MutableLiveData<>();

    /**
     * Mthod to implement BindingAdapters to load the image asynchronously into the item ImageView.
     *
     * @param imageView Album art image.
     * @param imageUrl  String image URL.
     */
    @BindingAdapter("imageurl")
    public static void setImageUrl(ImageView imageView, String imageUrl) {
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(imageView);
        }
    }
}
