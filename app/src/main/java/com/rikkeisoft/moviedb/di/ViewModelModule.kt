package com.rikkeisoft.moviedb.di

import androidx.lifecycle.ViewModel
import com.rikkeisoft.moviedb.ui.main.MainViewModel
import com.rikkeisoft.moviedb.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}
