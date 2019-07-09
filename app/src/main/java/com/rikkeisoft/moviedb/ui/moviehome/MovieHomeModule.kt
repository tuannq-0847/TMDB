package com.rikkeisoft.moviedb.ui.moviehome

import androidx.lifecycle.ViewModelProviders
import com.rikkeisoft.moviedb.utils.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Scope

@Module
class MovieHomeModule {

    @Provides
    @MovieHomeScope
    @Named(MovieHomeViewModel.NAME)
    fun provideViewModel(
        movieHomeFragment: MovieHomeFragment,
        viewModelFactory: MovieViewModelFactory
    ) = ViewModelProviders.of(movieHomeFragment, viewModelFactory)
        .get(MovieHomeViewModel::class.java)
}

@Scope
@Retention
annotation class MovieHomeScope
