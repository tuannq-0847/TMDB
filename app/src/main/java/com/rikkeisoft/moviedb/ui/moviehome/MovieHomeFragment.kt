package com.rikkeisoft.moviedb.ui.moviehome

import com.rikkeisoft.moviedb.R
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initViewModel() {
        if (::homeViewModel.isInitialized) {
            viewModel = homeViewModel
        }
    }
}