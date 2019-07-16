package com.rikkeisoft.moviedb.ui.dialog

import android.content.Context
import android.view.View
import android.view.View.OnClickListener
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.ui.base.BaseDialog
import kotlinx.android.synthetic.main.theme_dialog.checkBoxDark
import kotlinx.android.synthetic.main.theme_dialog.checkBoxLight
import kotlinx.android.synthetic.main.theme_dialog.textCancel

class ThemeDialog(
    layoutResId: Int, context: Context, private val listener: (isRefreshing: Boolean) -> Unit
) : BaseDialog(layoutResId, context), OnClickListener {

    init {
        checkBoxLight.setOnClickListener(this)
        checkBoxDark.setOnClickListener(this)
        textCancel.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.checkBoxLight -> {
                listener(true)
                dismiss()
            }
            R.id.checkBoxDark -> {
                listener(false)
                dismiss()
            }
            R.id.textCancel -> {
                dismiss()
            }
        }
    }
}