package com.rikkeisoft.moviedb.ui.moviehome

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.databinding.ItemMovieHomeChildBinding
import com.rikkeisoft.moviedb.ui.base.BaseRecyclerAdapter
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeChildAdapter.ChildMovieViewHolder

class MovieHomeChildAdapter(
    private val data: MutableList<MovieResult>,
    private val listener: (movieResult: MovieResult) -> Unit
) :
    BaseRecyclerAdapter<ItemMovieHomeChildBinding, MovieResult, ChildMovieViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildMovieViewHolder =
        ChildMovieViewHolder(
            getViewHolderDataBinding(parent, viewType) as ItemMovieHomeChildBinding
        )

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_movie_home_child

    fun setData(newData: MutableList<MovieResult>) {
        updateData(newData, DiffUtil.calculateDiff(DiffUtilMovieHomeCallBack(data, newData)))
    }

    inner class ChildMovieViewHolder(private val binding: ItemMovieHomeChildBinding) :
        BaseViewHolder<ItemMovieHomeChildBinding, MovieResult>(binding) {

        override fun bindView(position: Int, data: MovieResult) {
            itemView.setOnClickListener { listener(data) }
            binding.run {
                movieResult = data
            }
        }
    }
}
