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
import com.rikkeisoft.moviedb.R.layout
import com.rikkeisoft.moviedb.ui.dialog.NetworkErrorDialog
import com.rikkeisoft.moviedb.ui.dialog.ProcessLoadingDialog
import com.rikkeisoft.moviedb.utils.showMessage
import dagger.android.support.AndroidSupportInjection
import java.net.UnknownHostException
import androidx.appcompat.view.ContextThemeWrapper
import com.rikkeisoft.moviedb.R

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {
    @get:LayoutRes
    abstract val layoutId: Int

    protected open lateinit var viewModel: VM

    private val progressDialog by lazy { ProcessLoadingDialog(layout.layout_process_dialog, context!!) }

    private val networkDialog by lazy {
        NetworkErrorDialog(
            layout.network_error_dialog,
            context!!
        ) { isRefreshing -> listenerDialog(isRefreshing) }
    }

    lateinit var viewBinding: VB

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val context = ContextThemeWrapper(
            activity,
            R.style.AppTheme
        )

        val localInflater = inflater.cloneInContext(context)
        viewBinding = DataBindingUtil.inflate(localInflater, layoutId, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        viewBinding.run {
            lifecycleOwner = this@BaseFragment
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
            if (it is UnknownHostException) {
                showDialogNetworkError()
            } else {
                showError(it)
            }
        })
    }

    private fun showDialogNetworkError() {
        networkDialog.show()
    }

    protected abstract fun initViewModel()

    protected abstract fun initComponents()

    private fun showLoading(isLoading: Boolean) {
        when {
            isLoading -> progressDialog.show()
            else -> progressDialog.dismiss()
        }
    }

    private fun listenerDialog(isRefreshing: Boolean) {
        if (isRefreshing) {
            initComponents()
            doObserve()
        }
    }

    private fun showError(error: Throwable?) {
        context?.showMessage(error?.message.toString())
    }
}
