package com.rikkeisoft.moviedb.ui.main

import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.databinding.ActivityMainBinding
import com.rikkeisoft.moviedb.ui.base.BaseActivity
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeFragment
import com.rikkeisoft.moviedb.utils.add
import kotlinx.android.synthetic.main.activity_main.bottomNavigation
import javax.inject.Inject
import javax.inject.Named

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    override val layoutId: Int = R.layout.activity_main
    @Inject
    @field:Named(MainViewModel.NAME)
    lateinit var mainViewModel: MainViewModel

    override fun initComponents() {
        bottomNavigation.setOnNavigationItemSelectedListener(this)
        openMovieFragment()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemMovie -> {
                openMovieFragment()
            }
            R.id.itemSearch -> {

            }
            R.id.itemTV -> {

            }
        }
        return true
    }

    private fun openMovieFragment() {
        supportFragmentManager.add(R.id.layoutParent, MovieHomeFragment.newInstance())
    }

    override fun initViewModel() {
        if (::mainViewModel.isInitialized) {
            viewModel = mainViewModel
        }
    }
}
