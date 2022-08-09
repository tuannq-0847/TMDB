package com.karleinstein.moviedb.ui

import com.karleinstein.basemvvm.base.BaseViewModel
import io.reactivex.disposables.CompositeDisposable

open class CoreViewModel : BaseViewModel() {
    protected val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}