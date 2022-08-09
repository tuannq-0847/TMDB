package com.karleinstein.moviedb.ui.main

import android.content.Context
import android.graphics.Rect
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.karleinstein.basemvvm.base.BaseActivity
import com.karleinstein.basemvvm.utils.viewBinding
import com.karleinstein.moviedb.R
import com.karleinstein.moviedb.data.local.pref.AppPref
import com.karleinstein.moviedb.databinding.ActivityMainBinding
import com.karleinstein.moviedb.ui.moviehome.MovieHomeFragment
import com.karleinstein.moviedb.ui.search.SearchFragment
import com.rikkeisoft.moviedb.ui.dialog.ThemeDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.bottomNavigation
import kotlinx.android.synthetic.main.activity_main.toolbarMain

@AndroidEntryPoint
class MainActivity : BaseActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var appPref: AppPref

    private val themeDialog by lazy {
        ThemeDialog(appPref, this) { isChanged ->
            listenerThemeChange(isChanged)
        }
    }

    private fun checkPreference() = when {
        !appPref.getRef() -> delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        else -> delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
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

    override val viewBinding: ActivityMainBinding? by viewBinding(ActivityMainBinding::inflate)

    override fun bindView() {
        checkPreference()
        setSupportActionBar(toolbarMain)
        bottomNavigation.setOnNavigationItemSelectedListener(this)
        openMovieFragment()
    }

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        val view = currentFocus
        if (event?.action == MotionEvent.ACTION_DOWN && view is EditText) {
            val outRect = Rect()
            view.getGlobalVisibleRect(outRect)
            if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                view.clearFocus()
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
        return super.dispatchTouchEvent(event)
    }

    private fun openMovieFragment() {
        toolbarMain.title = resources.getString(R.string.movies)
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutChild, MovieHomeFragment())
            .commit()
    }

    private fun openFavoriteFragment() {
//        toolbarMain.title = resources.getString(R.string.favorite)
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.layoutChild, FavoriteMovieFragment())
//            .commit()
    }

    private fun openSearchFragment() {
        toolbarMain.title = resources.getString(R.string.search)
        supportFragmentManager.beginTransaction()
            .replace(R.id.layoutChild, SearchFragment())
            .commit()
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

    override fun setUpView() {
    }

    private fun listenerThemeChange(isChanged: Boolean) = if (isChanged) {
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
        appPref.saveRef(false)
    } else {
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
        appPref.saveRef(true)
    }
}
