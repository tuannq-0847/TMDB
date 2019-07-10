package com.rikkeisoft.moviedb.utils

import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.handleData(
    data: MutableLiveData<T>,
    error: MutableLiveData<Throwable>,
    loading: MutableLiveData<Boolean>
): Disposable =
    observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .doOnSubscribe {
            loading.value = true
        }
        .doAfterTerminate {
            loading.value = false
        }
        .subscribe(
            {
                data.value = it
            }, {
                error.value = it
            }
        )
