package com.karleinstein.moviedb.ui.dialog

import android.content.Context
import android.view.View
import android.view.View.OnClickListener
import androidx.viewbinding.ViewBinding
import com.karleinstein.basemvvm.base.BaseDialog
import com.karleinstein.moviedb.R
import kotlinx.android.synthetic.main.network_error_dialog.textRetry

class NetworkErrorDialog(context: Context, private val listener: (isRefreshing: Boolean) -> Unit) :
    BaseDialog(context), OnClickListener {

    init {
        textRetry.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.textRetry -> {
                listener(true)
                dismiss()
            }
        }
    }

    override val viewBinding: ViewBinding? = null
}

