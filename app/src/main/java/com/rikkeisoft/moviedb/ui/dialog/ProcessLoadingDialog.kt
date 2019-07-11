package com.rikkeisoft.moviedb.ui.dialog

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.rikkeisoft.moviedb.R

class ProcessLoadingDialog(context: Context) : Dialog(context) {
    init {
        initView()
    }

    private fun initView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_process_dialog)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}
