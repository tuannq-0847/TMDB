package com.rikkeisoft.moviedb.di

import android.app.Application
import com.rikkeisoft.moviedb.MainApplication
import com.rikkeisoft.moviedb.data.repository.MovieRepository
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        DataSourceModule::class]
)
interface AppComponent : AndroidInjector<MainApplication> {

    fun getMovieRepository(): MovieRepository

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
