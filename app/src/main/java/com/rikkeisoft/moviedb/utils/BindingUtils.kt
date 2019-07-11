package com.rikkeisoft.moviedb.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.rikkeisoft.moviedb.R

@BindingAdapter("handleProgressBar")
fun ProgressBar.handleProgress(isLoading: Boolean) {
    visibility = if (isLoading) View.VISIBLE else View.GONE
}

@BindingAdapter("showImagePoster")
fun ImageView.showPoster(url: String) {
    Glide.with(context)
        .load("${Constants.IMAGE_LINK}$url")
        .placeholder(R.drawable.background_image)
        .into(this)
}
