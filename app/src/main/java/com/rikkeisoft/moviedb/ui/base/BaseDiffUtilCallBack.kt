package com.rikkeisoft.moviedb.ui.base

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffUtilCallBack<T>(
    private val oldData: MutableList<T>,
    private val newData: MutableList<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldData.size

    override fun getNewListSize(): Int = newData.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }
}
