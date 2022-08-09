package com.karleinstein.moviedb.ui.detail

import com.karleinstein.basemvvm.base.BaseRecyclerAdapter
import com.karleinstein.basemvvm.base.BaseViewHolder
import com.karleinstein.moviedb.R
import com.karleinstein.moviedb.data.model.SearchResult
import com.karleinstein.moviedb.databinding.ItemCastingBinding

class DetailCastAdapter(
    listener: (searchResult: SearchResult) -> Unit
) : BaseRecyclerAdapter<SearchResult>(onClickItem = listener) {

    override fun buildLayoutRes(position: Int): Int {
        return R.layout.item_casting
    }

    override fun onBind(holder: BaseViewHolder, item: SearchResult, position: Int) {
        holder.binding<ItemCastingBinding>().apply {
            castDetail = item
        }
    }
}
