package com.example.learnaboutcountries.view;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.learnaboutcountries.R;

public class ImageLoadUtil {
    public static void loadImage(ImageView imageView, String url, CircularProgressDrawable drawable) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(drawable)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(url)
                .into(imageView);
    }

    public static CircularProgressDrawable getDrawable(Context context) {
        CircularProgressDrawable drawable = new CircularProgressDrawable(context);
        drawable.setStrokeWidth(10f);
        drawable.setCenterRadius(50f);
        drawable.start();
        return drawable;
    }
}
