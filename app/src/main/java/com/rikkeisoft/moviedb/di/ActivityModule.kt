package com.rikkeisoft.moviedb.di

import com.rikkeisoft.moviedb.ui.main.MainActivity
import com.rikkeisoft.moviedb.ui.main.MainActivityModule
import com.rikkeisoft.moviedb.ui.main.MainActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, FragmentModule::class])
    @MainActivityScope
    abstract fun contributeMainActivity(): MainActivity
}
