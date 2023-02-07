package com.rainbowt.traveltaipei.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.rainbowt.traveltaipei.R

@BindingAdapter("imageUrl")
fun loadImage(bmImage: ImageView, url: String?) {
    if (url == null) {
        bmImage.setImageDrawable(
            ContextCompat.getDrawable(bmImage.context, R.mipmap.not_found)
        )
        return
    }
    Glide.with(bmImage.context)
        .load(url)
        .into(bmImage)
}