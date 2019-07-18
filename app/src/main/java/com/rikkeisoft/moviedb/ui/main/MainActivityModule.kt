package com.rikkeisoft.moviedb.ui.main

import androidx.lifecycle.ViewModelProviders
import com.rikkeisoft.moviedb.utils.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Scope

@Module
class MainActivityModule {

    @Provides
    @MainActivityScope
    @Named(MainViewModel.NAME)
    fun provideViewModel(
        mainActivity: MainActivity,
        viewModelFactory: MovieViewModelFactory
    ) = ViewModelProviders.of(mainActivity, viewModelFactory)
        .get(MainViewModel::class.java)
}

@Scope
@Retention
annotation class MainActivityScope
