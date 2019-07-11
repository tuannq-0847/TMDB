package com.rikkeisoft.moviedb.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.rikkeisoft.moviedb.BR
import com.rikkeisoft.moviedb.ui.dialog.ProcessLoadingDialog
import com.rikkeisoft.moviedb.utils.showMessage
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    @get:LayoutRes
    abstract val layoutId: Int

    protected open lateinit var viewModel: VM

    private val progressDialog by lazy { ProcessLoadingDialog(context!!) }

    lateinit var viewBinding: VB

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        viewBinding.run {
            setVariable(BR.viewModel, viewModel)
        }
        initComponents()
        doObserve()
    }

    open fun doObserve() {
        viewModel.loading.observe(this, Observer {
            showLoading(it)
        })
        viewModel.error.observe(this, Observer {
            showError(it)
        })
    }

    protected abstract fun initViewModel()

    protected abstract fun initComponents()

    private fun showLoading(isLoading: Boolean) {
        when {
            isLoading -> progressDialog.show()
            else -> progressDialog.dismiss()
        }
    }

    private fun showError(error: Throwable?) {
        context?.showMessage(error?.message.toString())
    }
}
