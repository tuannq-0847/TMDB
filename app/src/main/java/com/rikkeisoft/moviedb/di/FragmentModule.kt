package com.rikkeisoft.moviedb.di

import com.rikkeisoft.moviedb.ui.detail.DetailFragment
import com.rikkeisoft.moviedb.ui.detail.DetailMovieModule
import com.rikkeisoft.moviedb.ui.detail.DetailMovieScope
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeFragment
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeModule
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector(modules = [MovieHomeModule::class])
    @MovieHomeScope
    abstract fun contributeMovieHomeFragment(): MovieHomeFragment

    @ContributesAndroidInjector(modules = [DetailMovieModule::class])
    @DetailMovieScope
    abstract fun contributeMovieDetailFragment(): DetailFragment
}
