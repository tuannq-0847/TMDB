package com.rikkeisoft.moviedb.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.SearchResult
import com.rikkeisoft.moviedb.databinding.ItemCastingBinding
import com.rikkeisoft.moviedb.ui.base.BaseRecyclerAdapter
import com.rikkeisoft.moviedb.ui.detail.DetailCastAdapter.CastViewHolder

class DetailCastAdapter(private val data: MutableList<SearchResult>) :
    BaseRecyclerAdapter<ItemCastingBinding, SearchResult, CastViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        CastViewHolder(
            getViewHolderDataBinding(parent, viewType) as ItemCastingBinding
        )

    fun setData(newData: MutableList<SearchResult>) {
        updateData(newData, DiffUtil.calculateDiff(DiffUtilCasting(data, newData)))
    }

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_casting

    class CastViewHolder(private val binding: ItemCastingBinding) :
        BaseViewHolder<ItemCastingBinding, SearchResult>(binding) {

        override fun bindView(position: Int, data: SearchResult) {
            binding.run {
                castDetail = data
            }
        }
    }
}
