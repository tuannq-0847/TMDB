package com.rikkeisoft.moviedb.ui.favorite

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.databinding.ItemFavoriteBinding
import com.rikkeisoft.moviedb.ui.base.BaseRecyclerAdapter
import com.rikkeisoft.moviedb.ui.favorite.FavoriteMovieAdapter.FavoriteViewHolder
import com.rikkeisoft.moviedb.ui.moviehome.DiffUtilMovieHomeCallBack

class FavoriteMovieAdapter(
    private val data: MutableList<MovieResult>,
    private val listener: (movieResult: MovieResult) -> Unit
) :
    BaseRecyclerAdapter<ItemFavoriteBinding, MovieResult, FavoriteViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder =
        FavoriteViewHolder(
            getViewHolderDataBinding(parent, viewType) as ItemFavoriteBinding
        )

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_favorite

    fun setData(newData: MutableList<MovieResult>) {
        updateData(
            newData, DiffUtil.calculateDiff(DiffUtilMovieHomeCallBack(data, newData))
        )
    }

    inner class FavoriteViewHolder(private val binding: ItemFavoriteBinding) :
        BaseViewHolder<ItemFavoriteBinding, MovieResult>(binding) {

        override fun bindView(position: Int, data: MovieResult) {
            binding.movie = data
            itemView.setOnClickListener { listener(data) }
        }
    }
}
