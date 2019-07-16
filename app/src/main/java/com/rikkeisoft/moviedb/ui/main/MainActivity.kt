package com.rikkeisoft.moviedb.ui.main

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import com.crashlytics.android.Crashlytics
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.databinding.ActivityMainBinding
import com.rikkeisoft.moviedb.ui.base.BaseActivity
import com.rikkeisoft.moviedb.ui.dialog.ThemeDialog
import com.rikkeisoft.moviedb.ui.favorite.FavoriteMovieFragment
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeFragment
import com.rikkeisoft.moviedb.ui.search.SearchFragment
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main.bottomNavigation
import kotlinx.android.synthetic.main.activity_main.toolbarMain
import javax.inject.Inject
import javax.inject.Named

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    override val layoutId: Int = R.layout.activity_main

    private val themeDialog by lazy {
        ThemeDialog(R.layout.theme_dialog, this) { isChanged ->
            listenerThemeChange(
                isChanged
            )
        }
    }
    @Inject
    @field:Named(MainViewModel.NAME)
    lateinit var mainViewModel: MainViewModel

    override fun initComponents() {
        setSupportActionBar(toolbarMain)
        bottomNavigation.setOnNavigationItemSelectedListener(this)
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
        toolbarMain.title = resources.getString(R.string.movies)
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutChild, MovieHomeFragment())
            .commit()
    }

    private fun openFavoriteFragment() {
        toolbarMain.title = resources.getString(R.string.favorite)
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutChild, FavoriteMovieFragment())
            .commit()
    }

    private fun openSearchFragment() {
        toolbarMain.title = resources.getString(R.string.search)
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutChild, SearchFragment())
            .commit()
    }

    override fun setUpFabric() {
        super.setUpFabric()
        Fabric.with(this, Crashlytics())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_settings, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemSetting -> {
                themeDialog.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initViewModel() {
        if (::mainViewModel.isInitialized) {
            viewModel = mainViewModel
        }
    }

    private fun listenerThemeChange(isChanged: Boolean) = if (isChanged) {
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
    } else {
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
    }
}
