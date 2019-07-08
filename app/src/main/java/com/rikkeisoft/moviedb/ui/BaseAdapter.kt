package com.rikkeisoft.moviedb.ui

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.rikkeisoft.moviedb.ui.BaseAdapter.Companion.BaseViewHolder

class BaseAdapter<T>(private val data: List<T>, private val VB: ViewDataBinding) :
    RecyclerView.Adapter<BaseViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> =
        BaseViewHolder(VB)

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