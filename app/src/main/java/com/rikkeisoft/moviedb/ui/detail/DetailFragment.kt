package com.rikkeisoft.moviedb.ui.detail

import android.os.Bundle
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.databinding.FragmentDetailScreenBinding
import com.rikkeisoft.moviedb.ui.base.BaseFragment
import javax.inject.Inject
import javax.inject.Named

class DetailFragment : BaseFragment<FragmentDetailScreenBinding, DetailViewModel>() {
    override val layoutId: Int = R.layout.fragment_detail_screen

    @Inject
    @field:Named(DetailViewModel.NAME)
    lateinit var detailViewModel: DetailViewModel

    override fun initViewModel() {
        if (::detailViewModel.isInitialized) {
            viewModel = detailViewModel
        }
    }

    override fun initComponents() {
        val data = arguments?.getParcelable<MovieResult>(ARGUMENT_MOVIE)
        data?.let {
            viewModel.setDetailMovie(it)
        }
    }

    companion object {

        const val ARGUMENT_MOVIE = "ARGUMENT_MOVIE"

        @JvmStatic
        fun newInstance(movieResult: MovieResult) = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_MOVIE, movieResult)
            }
        }
    }
}