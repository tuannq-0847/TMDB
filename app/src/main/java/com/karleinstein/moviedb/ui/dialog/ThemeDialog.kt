package com.rikkeisoft.moviedb.ui.dialog

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.karleinstein.basemvvm.base.BaseDialog
import com.karleinstein.basemvvm.extension.setOnClick
import com.karleinstein.basemvvm.utils.viewBinding
import com.karleinstein.moviedb.R
import com.karleinstein.moviedb.data.local.pref.AppPref
import com.karleinstein.moviedb.databinding.ThemeDialogBinding
import javax.inject.Inject
import kotlinx.android.synthetic.main.theme_dialog.checkBoxDark
import kotlinx.android.synthetic.main.theme_dialog.checkBoxLight
import kotlinx.android.synthetic.main.theme_dialog.textCancel

class ThemeDialog(
    var appPref: AppPref,
    context: Context,
    private val listener: (isRefreshing: Boolean) -> Unit
) : BaseDialog(context), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnClick(
            viewBinding?.checkBoxLight,
            viewBinding?.checkBoxLight,
            viewBinding?.checkBoxDark,
            viewBinding?.textCancel
        )
        checkBoxDark.setOnClickListener(this)
        textCancel.setOnClickListener(this)
        getStateCheckedBox()
        disableCheckedBox()
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

    private fun getStateCheckedBox() = when {
        !appPref.getRef() -> {
            checkBoxLight.isChecked = true
            checkBoxDark.isChecked = false
        }
        else -> {
            checkBoxLight.isChecked = false
            checkBoxDark.isChecked = true
        }
    }

    private fun disableCheckedBox() {
        when {
            checkBoxLight.isChecked -> {
                checkBoxLight.isEnabled = false
                checkBoxDark.isEnabled = true
            }
            checkBoxDark.isChecked -> {
                checkBoxDark.isEnabled = false
                checkBoxLight.isEnabled = true
            }
        }

    }

    override val viewBinding: ThemeDialogBinding? by viewBinding(ThemeDialogBinding::inflate)
}