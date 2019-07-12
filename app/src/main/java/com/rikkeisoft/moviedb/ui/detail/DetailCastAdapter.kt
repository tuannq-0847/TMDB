package com.rikkeisoft.moviedb.ui.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.CastDetailMovie.CastDetail
import com.rikkeisoft.moviedb.databinding.ItemCastingBinding
import com.rikkeisoft.moviedb.ui.base.BaseRecyclerAdapter
import com.rikkeisoft.moviedb.ui.detail.DetailCastAdapter.CastViewHolder
import com.rikkeisoft.moviedb.utils.Constants

class DetailCastAdapter(private val data: MutableList<CastDetail>) :
    BaseRecyclerAdapter<ItemCastingBinding, CastDetail, CastViewHolder>(data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder =
        CastViewHolder(
            getViewHolderDataBinding(parent, viewType) as ItemCastingBinding
        )

    fun setData(newData: MutableList<CastDetail>) {
        updateData(newData, DiffUtil.calculateDiff(DiffUtilCasting(data, newData)))
    }

    override fun getLayoutRes(viewType: Int): Int = R.layout.item_casting

    class CastViewHolder(private val binding: ItemCastingBinding) :
        BaseViewHolder<ItemCastingBinding, CastDetail>(binding) {

        override fun bindView(position: Int, data: CastDetail) {
            binding.run {
                castDetail = data
                constant = Constants
            }
        }
    }
}
