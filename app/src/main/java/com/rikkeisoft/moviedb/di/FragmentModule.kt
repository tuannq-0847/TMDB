package com.rikkeisoft.moviedb.di

import com.rikkeisoft.moviedb.ui.detail.DetailFragment
import com.rikkeisoft.moviedb.ui.detail.DetailMovieModule
import com.rikkeisoft.moviedb.ui.detail.DetailMovieScope
import com.rikkeisoft.moviedb.ui.favorite.FavoriteModule
import com.rikkeisoft.moviedb.ui.favorite.FavoriteMovieFragment
import com.rikkeisoft.moviedb.ui.favorite.FavoriteScope
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeFragment
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeModule
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeScope
import com.rikkeisoft.moviedb.ui.search.SearchFragment
import com.rikkeisoft.moviedb.ui.search.SearchModule
import com.rikkeisoft.moviedb.ui.search.SearchMovieScope
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

    @ContributesAndroidInjector(modules = [FavoriteModule::class])
    @FavoriteScope
    abstract fun contributeFavoriteFragment(): FavoriteMovieFragment

    @ContributesAndroidInjector(modules = [SearchModule::class])
    @SearchMovieScope
    abstract fun contributeSearchFragment(): SearchFragment
}
