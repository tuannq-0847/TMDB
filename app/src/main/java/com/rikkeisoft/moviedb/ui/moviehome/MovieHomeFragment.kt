package com.rikkeisoft.moviedb.ui.moviehome

import android.util.Log
import androidx.lifecycle.Observer
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieParent
import com.rikkeisoft.moviedb.databinding.FragmentMovieHomeBinding
import com.rikkeisoft.moviedb.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie_home.recyclerMovieHome
import javax.inject.Inject
import javax.inject.Named

class MovieHomeFragment : BaseFragment<FragmentMovieHomeBinding, MovieHomeViewModel>() {
    override val layoutId: Int = R.layout.fragment_movie_home
    private val adapter by lazy { MovieHomeParentAdapter(mutableListOf()) }
    private val data: ArrayList<MovieParent> = ArrayList()

    @Inject
    @field:Named(MovieHomeViewModel.NAME)
    lateinit var homeViewModel: MovieHomeViewModel

    override fun initComponents() {
        homeViewModel.getData()
        doObserve()
        bindView()
    }

    override fun initViewModel() {
        if (::homeViewModel.isInitialized) {
            viewModel = homeViewModel
        }
    }

    private fun bindView() {
        recyclerMovieHome.adapter = adapter
    }

    private fun doObserve() {
        homeViewModel.playingMovies.observe(this, Observer {
            data.add(MovieParent("test", it.results))
            adapter.setData(data)
        })
        homeViewModel.playingMovies.observe(this, Observer {
            data.add(MovieParent("test1", it.results))
            adapter.setData(data)
        })
        homeViewModel.playingMovies.observe(this, Observer {
            data.add(MovieParent("test2", it.results))
            adapter.setData(data)
        })
    }

    companion object {
        fun newInstance() = MovieHomeFragment()
    }
}
