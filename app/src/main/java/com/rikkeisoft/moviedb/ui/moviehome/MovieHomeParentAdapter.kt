package com.rikkeisoft.moviedb.ui.moviehome

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieParent
import com.rikkeisoft.moviedb.databinding.ItemMovieHomeBinding
import com.rikkeisoft.moviedb.ui.base.BaseRecyclerAdapter
import com.rikkeisoft.moviedb.ui.base.BaseRecyclerAdapter.Companion.BaseViewHolder
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeParentAdapter.MovieHomeViewHolder
import kotlinx.android.synthetic.main.item_movie_home.view.recyclerMovieChild

class MovieHomeParentAdapter(data: List<MovieParent>) :
    BaseRecyclerAdapter<ItemMovieHomeBinding, MovieParent, MovieHomeViewHolder>(data) {

    private val viewPool by lazy { RecyclerView.RecycledViewPool() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHomeViewHolder =
        MovieHomeViewHolder(
            getViewHolderDataBinding(parent, viewType) as ItemMovieHomeBinding
        )

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_movie_home

    override fun onBindViewHolder(holder: MovieHomeViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.recyclerMovieChild.setRecycledViewPool(viewPool)
    }

    inner class MovieHomeViewHolder(private val binding: ItemMovieHomeBinding) :
        BaseViewHolder<ItemMovieHomeBinding, MovieParent>(binding) {

        override fun bindView(position: Int, data: MovieParent) {
            binding.run {

            }
        }
    }
}
