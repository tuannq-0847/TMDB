package com.rikkeisoft.moviedb.ui.favorite

import androidx.lifecycle.ViewModelProviders
import com.rikkeisoft.moviedb.utils.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Scope

@Module
class FavoriteModule {

    @Provides
    @FavoriteScope
    @Named(FavoriteViewModel.NAME)
    fun provideViewModel(
        favoriteMovieFragment: FavoriteMovieFragment,
        viewModelFactory: MovieViewModelFactory
    ) = ViewModelProviders.of(favoriteMovieFragment, viewModelFactory)
        .get(FavoriteViewModel::class.java)
}

@Scope
@Retention
annotation class FavoriteScope
