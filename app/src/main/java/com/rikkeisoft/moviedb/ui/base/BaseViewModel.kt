package com.rikkeisoft.moviedb.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected open val compositeDisposable by lazy { CompositeDisposable() }
    open val loading by lazy { MutableLiveData<Boolean>() }
    open val error by lazy { MutableLiveData<Throwable>() }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
