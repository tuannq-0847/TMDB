package com.rikkeisoft.moviedb.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected open val compositeDisposable by lazy { CompositeDisposable() }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
