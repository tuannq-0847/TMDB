package com.rikkeisoft.moviedb.ui.dialog

import android.content.Context
import android.view.View
import android.view.View.OnClickListener
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.ui.base.BaseDialog
import kotlinx.android.synthetic.main.network_error_dialog.textRetry

class NetworkErrorDialog(layoutResId: Int, context: Context, private val listener: (isRefreshing: Boolean) -> Unit) :
    BaseDialog(layoutResId, context), OnClickListener {

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
}

