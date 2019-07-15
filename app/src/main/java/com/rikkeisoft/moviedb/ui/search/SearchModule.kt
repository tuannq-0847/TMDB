package com.rikkeisoft.moviedb.ui.search

import androidx.lifecycle.ViewModelProviders
import com.rikkeisoft.moviedb.utils.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Scope

@Module
class SearchModule {

    @Provides
    @SearchMovieScope
    @Named(SearchViewModel.NAME)
    fun provideViewModel(
        searchFragment: SearchFragment,
        viewModelFactory: MovieViewModelFactory
    ) = ViewModelProviders.of(searchFragment, viewModelFactory)
        .get(SearchViewModel::class.java)
}

@Scope
@Retention
annotation class SearchMovieScope