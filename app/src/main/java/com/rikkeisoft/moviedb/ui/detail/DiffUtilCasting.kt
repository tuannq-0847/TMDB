package com.rikkeisoft.moviedb.ui.detail

import com.rikkeisoft.moviedb.data.model.SearchResult
import com.rikkeisoft.moviedb.ui.base.BaseDiffUtilCallBack

class DiffUtilCasting(
    private val oldData: MutableList<SearchResult>,
    private val newData: MutableList<SearchResult>
) :
    BaseDiffUtilCallBack<SearchResult>(oldData, newData) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldData[oldItemPosition].castId == newData[newItemPosition].castId
}
