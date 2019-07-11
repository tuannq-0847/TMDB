package com.rikkeisoft.moviedb.ui.moviehome

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.Genre
import com.rikkeisoft.moviedb.databinding.ItemGenreBinding
import com.rikkeisoft.moviedb.ui.base.BaseRecyclerAdapter
import com.rikkeisoft.moviedb.ui.base.BaseRecyclerAdapter.Companion.BaseViewHolder
import com.rikkeisoft.moviedb.ui.moviehome.MovieGenreAdapter.GenreViewHolder
import kotlinx.android.synthetic.main.item_genre.view.textGenre

class MovieGenreAdapter(private val data: MutableList<Genre>) :
    BaseRecyclerAdapter<ItemGenreBinding, Genre, GenreViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
        GenreViewHolder(getViewHolderDataBinding(parent, viewType) as ItemGenreBinding)

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_genre

    fun setData(newData: MutableList<Genre>) {
        updateData(newData, DiffUtil.calculateDiff(DiffUtilGenreCallBack(data, newData)))
    }

    class GenreViewHolder(binding: ItemGenreBinding) : BaseViewHolder<ItemGenreBinding, Genre>(binding) {
        override fun bindView(position: Int, data: Genre) {
            itemView.run {
                textGenre.text = data.nameGenre
            }
        }
    }
}
