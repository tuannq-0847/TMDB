package com.rikkeisoft.moviedb.ui.moviehome

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.databinding.ItemMovieHomeChildBinding
import com.rikkeisoft.moviedb.ui.base.BaseRecyclerAdapter
import com.rikkeisoft.moviedb.ui.base.BaseRecyclerAdapter.Companion.BaseViewHolder
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeChildAdapter.ChildMovieViewHolder

class MovieHomeChildAdapter(private val data: MutableList<MovieResult>) :
    BaseRecyclerAdapter<ItemMovieHomeChildBinding, MovieResult, ChildMovieViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildMovieViewHolder =
        ChildMovieViewHolder(
            getViewHolderDataBinding(parent, viewType) as ItemMovieHomeChildBinding
        )

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_movie_home_child

    fun setData(newData: MutableList<MovieResult>) {
        updateData(newData, DiffUtil.calculateDiff(DiffUtilMovieHomeCallBack(data, newData)))
    }

    class ChildMovieViewHolder(private val binding: ItemMovieHomeChildBinding) :
        BaseViewHolder<ItemMovieHomeChildBinding, MovieResult>(binding) {

        override fun bindView(position: Int, data: MovieResult) {
            binding.run {
                movieResult = data
            }
        }
    }
}
