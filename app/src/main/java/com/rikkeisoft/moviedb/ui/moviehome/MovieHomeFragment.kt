package com.rikkeisoft.moviedb.ui.moviehome

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieParent
import com.rikkeisoft.moviedb.databinding.FragmentMovieHomeBinding
import com.rikkeisoft.moviedb.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie_home.recyclerGenres
import kotlinx.android.synthetic.main.fragment_movie_home.recyclerMovieHome
import javax.inject.Inject
import javax.inject.Named

class MovieHomeFragment : BaseFragment<FragmentMovieHomeBinding, MovieHomeViewModel>() {
    override val layoutId: Int = R.layout.fragment_movie_home
    private val adapter by lazy { MovieHomeParentAdapter(mutableListOf()) }
    private val genreAdapter by lazy { MovieGenreAdapter(mutableListOf()) }
    private val data: ArrayList<MovieParent> = ArrayList()

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
        recyclerGenres.adapter = genreAdapter
    }

    override fun doObserve() {
        super.doObserve()
        homeViewModel.movieData.observe(this, Observer {
            adapter.setData(it)
        })
        homeViewModel.genres.observe(this, Observer {
            genreAdapter.setData(it.genres)
        })
    }

    companion object {
        fun newInstance() = MovieHomeFragment()
    }
}
