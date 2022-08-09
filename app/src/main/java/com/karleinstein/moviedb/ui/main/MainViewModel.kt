package com.karleinstein.moviedb.ui.main

import com.karleinstein.moviedb.ui.CoreViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : CoreViewModel() {

    companion object {
        const val NAME = "MainViewModel"
    }
}
