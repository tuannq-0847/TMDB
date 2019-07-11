package com.rikkeisoft.moviedb.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rikkeisoft.moviedb.ui.detail.DetailViewModel
import com.rikkeisoft.moviedb.ui.main.MainViewModel
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeViewModel
import com.rikkeisoft.moviedb.utils.MovieViewModelFactory
import com.rikkeisoft.moviedb.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieHomeViewModel::class)
    abstract fun bindMovieHomeViewModel(movieHomeViewModel: MovieHomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(
        viewModelFactory: MovieViewModelFactory
    ): ViewModelProvider.Factory
}
