package com.rikkeisoft.moviedb.ui.detail

import android.app.Application
import com.rikkeisoft.moviedb.ui.base.BaseViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
    companion object {
        const val NAME = "DetailViewModel"
    }
}
