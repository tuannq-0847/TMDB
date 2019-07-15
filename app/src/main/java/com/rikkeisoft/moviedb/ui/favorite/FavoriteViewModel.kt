package com.rikkeisoft.moviedb.ui.favorite

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.data.repository.FavoriteRepository
import com.rikkeisoft.moviedb.ui.base.BaseViewModel
import com.rikkeisoft.moviedb.utils.handleLoading
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository,
    application: Application
) : BaseViewModel(application) {

    val favorites by lazy { MutableLiveData<MutableList<MovieResult>>() }

    fun getAllFavorites() {
        compositeDisposable.add(
            favoriteRepository.getAllFavorites()
                .handleLoading(loading)
                .subscribe({
                    favorites.value = it
                }, {
                    error.value = it
                })
        )
    }

    companion object {
        const val NAME = "FavoriteViewModel"
    }
}
