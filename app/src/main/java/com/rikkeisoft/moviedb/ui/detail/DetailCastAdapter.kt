package com.rikkeisoft.moviedb.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.SearchResult
import com.rikkeisoft.moviedb.databinding.ItemCastingBinding
import com.rikkeisoft.moviedb.ui.base.BaseRecyclerAdapter
import com.rikkeisoft.moviedb.ui.detail.DetailCastAdapter.CastViewHolder

class DetailCastAdapter(
    private val data: MutableList<SearchResult>,
    private val listener: (searchResult: SearchResult) -> Unit
) :
    BaseRecyclerAdapter<ItemCastingBinding, SearchResult, CastViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        CastViewHolder(
            getViewHolderDataBinding(parent, viewType) as ItemCastingBinding
        )

    fun setData(newData: MutableList<SearchResult>) {
        updateData(newData, DiffUtil.calculateDiff(DiffUtilCasting(data, newData)))
    }

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_casting

    inner class CastViewHolder(private val binding: ItemCastingBinding) :
        BaseViewHolder<ItemCastingBinding, SearchResult>(binding) {

        override fun bindView(position: Int, data: SearchResult) {
            itemView.setOnClickListener { listener(data) }
            binding.run {
                castDetail = data
            }
        }
    }
}
