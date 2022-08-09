//package com.karleinstein.moviedb.ui.favorite
//
//import android.app.Application
//import androidx.lifecycle.MutableLiveData
//import com.karleinstein.moviedb.data.model.MovieResult
//import com.karleinstein.moviedb.ui.base.BaseViewModel
//import com.karleinstein.moviedb.utils.handleLoading
//import javax.inject.Inject
//
//class FavoriteViewModel @Inject constructor(
//    private val favoriteRepository: FavoriteRepository,
//    application: Application
//) : BaseViewModel(application) {
//
//    val favorites by lazy { MutableLiveData<MutableList<MovieResult>>() }
//
//    fun getAllFavorites() {
//        compositeDisposable.add(
//            favoriteRepository.getAllFavorites()
//                .handleLoading(loading)
//                .subscribe({
//                    favorites.value = it
//                }, {
//                    error.value = it
//                })
//        )
//    }
//
//    companion object {
//        const val NAME = "FavoriteViewModel"
//    }
//}
