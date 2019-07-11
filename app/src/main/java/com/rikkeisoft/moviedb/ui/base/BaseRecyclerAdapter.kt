package com.rikkeisoft.moviedb.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rikkeisoft.moviedb.ui.base.BaseRecyclerAdapter.BaseViewHolder

abstract class BaseRecyclerAdapter<ViewBinding : ViewDataBinding, T, VH : BaseViewHolder<ViewBinding, T>>(private val data: MutableList<T>) :
    RecyclerView.Adapter<VH>() {

    override fun getItemCount(): Int = data.size

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    protected abstract fun getLayoutRes(viewType: Int): Int

    protected fun getViewHolderDataBinding(parent: ViewGroup, viewType: Int): ViewDataBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getLayoutRes(viewType),
            parent, false
        )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindView(position, data[position])
    }

    open fun updateData(newData: List<T>, diffResult: DiffUtil.DiffResult) {
        data.clear()
        data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    abstract class BaseViewHolder<ViewBinding : ViewDataBinding, T>(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        abstract fun bindView(position: Int, data: T)
    }
}
