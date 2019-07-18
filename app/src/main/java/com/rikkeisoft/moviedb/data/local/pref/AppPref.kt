package com.rikkeisoft.moviedb.data.local.pref

import android.app.Application
import android.content.Context
import javax.inject.Singleton

@Singleton
class AppPref(application: Application) {

    private val sharedPreference = application.getSharedPreferences(application.packageName, Context.MODE_PRIVATE)

    fun saveRef(isDark: Boolean) {
        sharedPreference.edit().putBoolean(THEME, isDark)
            .apply()
    }

    fun getRef(): Boolean =
        sharedPreference.getBoolean(THEME, false)

    companion object {
        const val THEME = "theme"
    }
}
