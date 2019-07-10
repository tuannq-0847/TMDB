package com.rikkeisoft.moviedb.ui.moviehome

import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.ui.base.BaseDiffUtilCallBack

class DiffUtilMovieHomeCallBack(
    private val oldData: MutableList<MovieResult>,
    private val newData: MutableList<MovieResult>
) :
    BaseDiffUtilCallBack<MovieResult>(oldData, newData) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition].idMovie == newData[newItemPosition].idMovie
}
