package com.rikkeisoft.moviedb.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    protected open val compositeDisposable by lazy { CompositeDisposable() }
    open val loading by lazy { MutableLiveData<Boolean>() }
    open val error by lazy { MutableLiveData<Throwable>() }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

//test ci only
