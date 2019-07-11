package com.rikkeisoft.moviedb.ui.detail

import androidx.lifecycle.ViewModelProviders
import com.rikkeisoft.moviedb.utils.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Scope

@Module
class DetailMovieModule {

    @Provides
    @DetailMovieScope
    @Named(DetailViewModel.NAME)
    fun provideViewModel(
        detailFragment: DetailFragment,
        viewModelFactory: MovieViewModelFactory
    ) = ViewModelProviders.of(detailFragment, viewModelFactory)
        .get(DetailViewModel::class.java)
}

@Scope
@Retention
annotation class DetailMovieScope
