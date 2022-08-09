package com.karleinstein.moviedb.utils

import com.karleinstein.moviedb.ui.CoreViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.handleLoading(
    viewModel: CoreViewModel
): Single<T> =
    observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .doOnSubscribe {
            viewModel.showLoading(true)
        }
        .doAfterTerminate {
            viewModel.showLoading(false)
        }

