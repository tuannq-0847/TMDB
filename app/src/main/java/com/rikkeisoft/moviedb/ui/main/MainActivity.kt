package com.rikkeisoft.moviedb.ui.main

import android.os.Bundle
import android.view.MenuItem
import com.crashlytics.android.Crashlytics
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.databinding.ActivityMainBinding
import com.rikkeisoft.moviedb.ui.base.BaseActivity
import com.rikkeisoft.moviedb.ui.favorite.FavoriteMovieFragment
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeFragment
import com.rikkeisoft.moviedb.ui.search.SearchFragment
import io.fabric.sdk.android.Fabric
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openMovieFragment()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemMovie -> {
                openMovieFragment()
            }
            R.id.itemSearch -> {
                openSearchFragment()
            }
            R.id.itemFavorite -> {
                openFavoriteFragment()
            }
        }
        return true
    }

    private fun openMovieFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutChild, MovieHomeFragment())
            .commit()
    }

    private fun openFavoriteFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutChild, FavoriteMovieFragment())
            .commit()
    }

    private fun openSearchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutChild, SearchFragment())
            .commit()
    }

    override fun setUpFabric() {
        super.setUpFabric()
        Fabric.with(this, Crashlytics())
    }

    override fun initViewModel() {
        if (::mainViewModel.isInitialized) {
            viewModel = mainViewModel
        }
    }
}
