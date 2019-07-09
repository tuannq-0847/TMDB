package com.rikkeisoft.moviedb.ui.main

import android.util.Log
import androidx.lifecycle.Observer
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.databinding.ActivityMainBinding
import com.rikkeisoft.moviedb.ui.base.BaseActivity
import javax.inject.Inject
import javax.inject.Named

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutId: Int = R.layout.activity_main
    @Inject
    @field:Named(MainViewModel.NAME)
    lateinit var mainViewModel: MainViewModel

    override fun initComponents() {
    }

    override fun initViewModel() {
        if (::mainViewModel.isInitialized) {
            viewModel = mainViewModel
        }
    }
}
