package com.karleinstein.moviedb.utils

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.karleinstein.basemvvm.extension.loadImage
import com.karleinstein.moviedb.R

@BindingAdapter("showImagePoster", "sizeImage")
fun ImageView.showPoster(url: String?, sizeImage: String?) {
    loadImage("${Constants.IMAGE_LINK}$sizeImage$url",R.drawable.bg_default_image)
}

@BindingAdapter("handleRatingBar")
fun RatingBar.handleRating(score: Double) {
    stepSize = 0.5f
    setIsIndicator(true)
    rating = (score * 0.5).toFloat()
    invalidate()
}

@BindingAdapter("favoriteStatus")
fun FloatingActionButton.handleFavoriteStatus(isFavorite: Boolean) {
    if (isFavorite) {
        setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_white, context.theme))
    } else {
        setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border_white, context.theme))
    }
}
