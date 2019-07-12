package com.rikkeisoft.moviedb.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.databinding.ItemMovieHomeChildBinding
import com.rikkeisoft.moviedb.ui.base.BaseRecyclerAdapter
import com.rikkeisoft.moviedb.ui.detail.DetailSimilarAdapter.DetailSimilarViewHolder
import com.rikkeisoft.moviedb.ui.moviehome.DiffUtilMovieHomeCallBack

class DetailSimilarAdapter(private val data: MutableList<MovieResult>) :
    BaseRecyclerAdapter<ItemMovieHomeChildBinding, MovieResult, DetailSimilarViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailSimilarViewHolder =
        DetailSimilarViewHolder(
            getViewHolderDataBinding(parent, viewType) as ItemMovieHomeChildBinding
        )

    fun setData(newData: MutableList<MovieResult>) {
        updateData(newData, DiffUtil.calculateDiff(DiffUtilMovieHomeCallBack(data, newData)))
    }

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_movie_home_child
    class DetailSimilarViewHolder(private val binding: ItemMovieHomeChildBinding) :
        BaseViewHolder<ItemMovieHomeChildBinding, MovieResult>(binding) {

        override fun bindView(position: Int, data: MovieResult) {
            binding.movieResult = data
        }
    }
}
