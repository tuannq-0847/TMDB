package com.karleinstein.moviedb.ui.moviehome

import com.karleinstein.basemvvm.base.BaseRecyclerAdapter
import com.karleinstein.basemvvm.base.BaseViewHolder
import com.karleinstein.moviedb.R
import com.karleinstein.moviedb.data.model.Genre
import com.karleinstein.moviedb.databinding.ItemGenreBinding

class MovieGenreAdapter() :
    BaseRecyclerAdapter<Genre>() {
    override fun buildLayoutRes(position: Int): Int = R.layout.item_genre

    override fun onBind(holder: BaseViewHolder, item: Genre, position: Int) {
        holder.binding<ItemGenreBinding>().run {
            textGenre.text = item.nameGenre
        }
    }
}
