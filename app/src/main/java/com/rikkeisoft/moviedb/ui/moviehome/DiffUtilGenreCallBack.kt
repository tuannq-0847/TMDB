package com.rikkeisoft.moviedb.ui.moviehome

import com.rikkeisoft.moviedb.data.model.Genre
import com.rikkeisoft.moviedb.ui.base.BaseDiffUtilCallBack

class DiffUtilGenreCallBack(
    private val oldData: MutableList<Genre>,
    private val newData: MutableList<Genre>
) :
    BaseDiffUtilCallBack<Genre>(oldData, newData) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition].idGenre == newData[newItemPosition].idGenre
}
