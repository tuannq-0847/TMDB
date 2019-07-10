package com.rikkeisoft.moviedb.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.rikkeisoft.moviedb.BR
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : DaggerAppCompatActivity() {
    @get:LayoutRes
    abstract val layoutId: Int

    protected open lateinit var viewModel: VM

    lateinit var viewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewModel()
        viewBinding = DataBindingUtil.setContentView(this, layoutId)
        viewBinding.setVariable(BR.viewModel, viewModel)
        initComponents()
    }

    protected abstract fun initViewModel()

    protected abstract fun initComponents()
}
