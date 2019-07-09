package com.rikkeisoft.moviedb.ui.main

import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.databinding.ActivityMainBinding
import com.rikkeisoft.moviedb.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutId: Int = R.layout.activity_main
    override val viewModel: MainViewModel
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}
