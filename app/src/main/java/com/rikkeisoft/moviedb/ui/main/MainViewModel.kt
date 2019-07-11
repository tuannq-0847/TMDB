package com.rikkeisoft.moviedb.ui.main

import android.app.Application
import com.rikkeisoft.moviedb.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(application: Application) : BaseViewModel(application) {

    companion object {
        const val NAME = "MainViewModel"
    }
}
