package com.rikkeisoft.moviedb.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rikkeisoft.moviedb.ui.base.BaseAdapter.Companion.BaseViewHolder

abstract class BaseAdapter<T>(private val data: List<T>, private val VB: ViewDataBinding) :
    RecyclerView.Adapter<BaseViewHolder<T>>() {

    protected abstract fun getLayoutRes(viewType: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getLayoutRes(viewType),
                parent, false
            )
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBind(position, data[position])
    }

    companion object {
        open class BaseViewHolder<T>(VB: ViewDataBinding) : RecyclerView.ViewHolder(VB.root) {
            open fun onBind(position: Int, data: T) {
            }
        }
    }
}