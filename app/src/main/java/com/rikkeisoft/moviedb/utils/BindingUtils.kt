package com.rikkeisoft.moviedb.utils

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

@BindingAdapter("handleProgressBar")
fun ProgressBar.handleProgress(isLoading: Boolean) {
    visibility = if (isLoading) View.VISIBLE else View.GONE
}
