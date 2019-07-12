package com.rikkeisoft.moviedb.ui.detail

import com.rikkeisoft.moviedb.data.model.CastDetailMovie.CastDetail
import com.rikkeisoft.moviedb.ui.base.BaseDiffUtilCallBack

class DiffUtilCasting(private val oldData: MutableList<CastDetail>, private val newData: MutableList<CastDetail>) :
    BaseDiffUtilCallBack<CastDetail>(oldData, newData) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition].castId == newData[newItemPosition].castId
}
