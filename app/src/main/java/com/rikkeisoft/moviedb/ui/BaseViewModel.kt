package com.rikkeisoft.moviedb.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class BaseViewModel : ViewModel() {
    val compositeDisposable: CompositeDisposable by lazy { CompositeDisposable() }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
