package com.rikkeisoft.moviedb.data.remote.response

import com.rikkeisoft.moviedb.data.remote.response.StatusResponse.ERROR
import com.rikkeisoft.moviedb.data.remote.response.StatusResponse.LOADING
import com.rikkeisoft.moviedb.data.remote.response.StatusResponse.SUCCESS

class APIResponse<T>(
    val status: StatusResponse,
    val data: T?,
    val error: Throwable?
) {

    companion object {
        fun <T> loading() = APIResponse<T>(LOADING, null, null)
        fun <T> success(data: T?) = APIResponse<T>(SUCCESS, data, null)
        fun <T> error(e: Throwable?) = APIResponse<T>(ERROR, null, e)
    }
}
