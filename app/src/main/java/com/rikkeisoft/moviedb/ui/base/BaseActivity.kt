package com.rikkeisoft.moviedb.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.rikkeisoft.moviedb.BR
import com.rikkeisoft.moviedb.utils.showMessage
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
        setUpFabric()
        initViewModel()
        viewBinding = DataBindingUtil.setContentView(this, layoutId)
        viewBinding.setVariable(BR.viewModel, viewModel)
        initComponents()
    }

    protected open fun setUpFabric() {
    }

    protected abstract fun initViewModel()

    protected abstract fun initComponents()

    open fun showLoading(isLoading: Boolean) {
        viewModel.loading.value = isLoading
    }

    open fun showError(error: Throwable?) {
        viewModel.error.value = error
        showMessage(error?.message.toString())
    }
}
