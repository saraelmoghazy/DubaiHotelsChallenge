package com.hotels.tajawal.dubaihotels.util;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hotels.tajawal.dubaihotels.TajawalApp;


/**
 * Created by Sara Elmoghazy.
 * Helper Class for Binding actions to views given custom attributes.
 **/
public class CommonBindingUtils {

    /**
     * Set image inside image view given image url
     *
     * @param imageView
     * @param imageUrl
     */
    @BindingAdapter(value = {"imageUrl", "placeholder"}, requireAll = false)
    public static void setImageUrl(ImageView imageView, String imageUrl, Drawable placeholder) {
        Glide.with(TajawalApp.getInstance()).load(imageUrl)
                .apply(new RequestOptions().placeholder(placeholder).error(placeholder))
                .into(imageView);
    }
}
