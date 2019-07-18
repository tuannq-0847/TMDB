package com.rikkeisoft.moviedb.ui.detail_actor

import androidx.lifecycle.ViewModelProviders
import com.rikkeisoft.moviedb.utils.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Scope

@Module
class DetailActorModule {

    @Provides
    @DetailActorMovieScope
    @Named(DetailActorViewModel.NAME)
    fun provideViewModel(
        detailActorFragment: DetailActorFragment,
        viewModelFactory: MovieViewModelFactory
    ) = ViewModelProviders.of(detailActorFragment, viewModelFactory)
        .get(DetailActorViewModel::class.java)
}

@Scope
@Retention
annotation class DetailActorMovieScope
