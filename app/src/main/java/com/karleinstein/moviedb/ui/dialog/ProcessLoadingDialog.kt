package com.karleinstein.moviedb.ui.dialog

import android.content.Context
import androidx.viewbinding.ViewBinding
import com.karleinstein.basemvvm.base.BaseDialog

class ProcessLoadingDialog(
    layoutResId: Int, context: Context
) : BaseDialog(context) {
    override val viewBinding: ViewBinding?
        get() = null
}
