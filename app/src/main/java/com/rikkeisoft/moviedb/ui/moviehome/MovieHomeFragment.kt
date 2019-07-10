package com.rikkeisoft.moviedb.ui.moviehome

import androidx.lifecycle.Observer
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.remote.response.GetMovieListResponse
import com.rikkeisoft.moviedb.data.remote.response.StatusResponse.ERROR
import com.rikkeisoft.moviedb.data.remote.response.StatusResponse.LOADING
import com.rikkeisoft.moviedb.data.remote.response.StatusResponse.SUCCESS
import com.rikkeisoft.moviedb.databinding.FragmentMovieHomeBinding
import com.rikkeisoft.moviedb.ui.base.BaseFragment
import javax.inject.Inject
import javax.inject.Named

class MovieHomeFragment : BaseFragment<FragmentMovieHomeBinding, MovieHomeViewModel>() {
    override val layoutId: Int = R.layout.fragment_movie_home
    @Inject
    @field:Named(MovieHomeViewModel.NAME)
    lateinit var homeViewModel: MovieHomeViewModel

    override fun initComponents() {
        homeViewModel.getData()
        doObserve()
    }

    private fun doObserve() {
        homeViewModel.movieData.observe(this, Observer {
            when (it.status) {
                LOADING -> showLoading(true)
                SUCCESS -> showSuccess(it.data)
                ERROR -> showError(it.error)
            }
        })
    }

    private fun showSuccess(data: GetMovieListResponse?) {

    }

    override fun initViewModel() {
        if (::homeViewModel.isInitialized) {
            viewModel = homeViewModel
        }
    }

    companion object {
        fun newInstance() = MovieHomeFragment()
    }
}
