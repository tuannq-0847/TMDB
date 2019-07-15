package com.rikkeisoft.moviedb.ui.base

import android.app.Dialog
import android.content.Context
import android.view.Window

open class BaseDialog(
    private val layoutResId: Int, context: Context) : Dialog(context) {

    init {
        initView()
    }

    private fun initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(layoutResId)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}
