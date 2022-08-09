package com.karleinstein.moviedb.ui.moviehome

import androidx.recyclerview.widget.RecyclerView
import com.karleinstein.basemvvm.base.BaseRecyclerAdapter
import com.karleinstein.basemvvm.base.BaseViewHolder
import com.karleinstein.moviedb.R
import com.karleinstein.moviedb.data.model.MovieParent
import com.karleinstein.moviedb.data.model.MovieResult
import com.karleinstein.moviedb.databinding.ItemMovieHomeBinding

class MovieHomeParentAdapter(
    private val listener: (movieResult: MovieResult) -> Unit
) : BaseRecyclerAdapter< MovieParent>() {

    private val viewPool by lazy { RecyclerView.RecycledViewPool() }

    override fun buildLayoutRes(position: Int): Int = R.layout.item_movie_home

    override fun onBind(holder: BaseViewHolder, item: MovieParent, position: Int) {
        holder.binding<ItemMovieHomeBinding>().run {
            recyclerMovieChild.setRecycledViewPool(viewPool)
            recyclerMovieChild.adapter = MovieHomeChildAdapter(listener).apply {
                submitList(item.movieResults)
            }
            recyclerMovieChild.isNestedScrollingEnabled = true
            textNameChild.text = item.kindOfMovie
        }
    }
}
