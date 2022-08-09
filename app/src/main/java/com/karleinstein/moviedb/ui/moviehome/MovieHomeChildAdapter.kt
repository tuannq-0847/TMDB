package com.karleinstein.moviedb.ui.moviehome

import com.karleinstein.basemvvm.base.BaseRecyclerAdapter
import com.karleinstein.basemvvm.base.BaseViewHolder
import com.karleinstein.moviedb.R
import com.karleinstein.moviedb.data.model.MovieResult
import com.karleinstein.moviedb.databinding.ItemMovieHomeChildBinding

class MovieHomeChildAdapter(
    listener: (movieResult: MovieResult) -> Unit
) : BaseRecyclerAdapter<MovieResult>(onClickItem = listener) {

    override fun buildLayoutRes(position: Int) = R.layout.item_movie_home_child

    override fun onBind(holder: BaseViewHolder, item: MovieResult, position: Int) {
        holder.binding<ItemMovieHomeChildBinding>().run {
            movieResult = item
        }
    }
}
