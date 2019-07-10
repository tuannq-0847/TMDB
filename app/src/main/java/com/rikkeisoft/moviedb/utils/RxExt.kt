package com.rikkeisoft.moviedb.utils

import androidx.lifecycle.MutableLiveData
import com.rikkeisoft.moviedb.data.remote.response.APIResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.handleData(mutableLiveData: MutableLiveData<APIResponse<T>>): Disposable =
    observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .doOnSuccess {
            mutableLiveData.value = APIResponse.loading()
        }
        .subscribe(
            {
                mutableLiveData.value = APIResponse.success(it)
            }, {
                mutableLiveData.value = APIResponse.error(it)
            }
        )
