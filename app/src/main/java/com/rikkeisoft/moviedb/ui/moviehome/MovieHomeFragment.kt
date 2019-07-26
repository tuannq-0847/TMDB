package com.rikkeisoft.moviedb.ui.moviehome

import android.view.View
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.databinding.FragmentMovieHomeBinding
import com.rikkeisoft.moviedb.ui.base.BaseFragment
import com.rikkeisoft.moviedb.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_movie_home.recyclerGenres
import kotlinx.android.synthetic.main.fragment_movie_home.recyclerMovieHome
import javax.inject.Inject
import javax.inject.Named

class MovieHomeFragment : BaseFragment<FragmentMovieHomeBinding, MovieHomeViewModel>() {
    override val layoutId: Int = R.layout.fragment_movie_home
    private val adapter by lazy {
        MovieHomeParentAdapter(mutableListOf()) { movieResult, view, position ->
            onItemMovieClick(
                movieResult, view, position
            )
        }
    }
    private val genreAdapter by lazy { MovieGenreAdapter(mutableListOf()) }

    @Inject
    @field:Named(MovieHomeViewModel.NAME)
    lateinit var homeViewModel: MovieHomeViewModel

    override fun initComponents() {
        homeViewModel.getData()
        bindView()
    }

    override fun initViewModel() {
        if (::homeViewModel.isInitialized) {
            viewModel = homeViewModel
        }
    }

    private fun bindView() {
        recyclerMovieHome.adapter = adapter
        recyclerMovieHome.isNestedScrollingEnabled = true
        recyclerGenres.adapter = genreAdapter
        recyclerGenres.isNestedScrollingEnabled = true
    }

    override fun doObserve() {
        super.doObserve()
        viewModel.movieData.observe(this, Observer {
            adapter.setData(it)
        })
        viewModel.genres.observe(this, Observer {
            genreAdapter.setData(it.genres)
        })
    }

    private fun onItemMovieClick(movieResult: MovieResult, view: View, position: Int) {
        val instanceFragment = DetailFragment.newInstance(movieResult,position)
        this.sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.fade)
        this.exitTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.fade)

        instanceFragment.sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.fade)
        instanceFragment.enterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.fade)
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.add(R.id.layoutParent, instanceFragment)
            ?.addSharedElement(view, "transition$position")
            ?.addToBackStack(null)
            ?.commit()
    }
}
