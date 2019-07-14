package com.rikkeisoft.moviedb

import com.rikkeisoft.moviedb.di.AppComponent
import com.rikkeisoft.moviedb.di.DaggerAppComponent
import com.rikkeisoft.moviedb.di.DatabaseModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().application(this).build()
}
