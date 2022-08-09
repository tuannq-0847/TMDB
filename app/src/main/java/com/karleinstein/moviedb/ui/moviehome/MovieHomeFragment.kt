package com.karleinstein.moviedb.ui.moviehome

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.karleinstein.basemvvm.base.BaseFragment
import com.karleinstein.basemvvm.extension.addFragment
import com.karleinstein.basemvvm.utils.viewBinding
import com.karleinstein.moviedb.R
import com.karleinstein.moviedb.data.local.pref.AppPref
import com.karleinstein.moviedb.data.model.MovieResult
import com.karleinstein.moviedb.databinding.FragmentMovieHomeBinding
import com.karleinstein.moviedb.ui.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_movie_home.recyclerGenres
import kotlinx.android.synthetic.main.fragment_movie_home.recyclerMovieHome

@AndroidEntryPoint
class MovieHomeFragment : BaseFragment(R.layout.fragment_movie_home) {

    @Inject
    lateinit var appPref: AppPref

    private val adapter by lazy {
        MovieHomeParentAdapter { movieResult ->
            onItemMovieClick(
                movieResult
            )
        }
    }
    private val genreAdapter by lazy { MovieGenreAdapter() }
    override val viewBinding: FragmentMovieHomeBinding? by viewBinding(FragmentMovieHomeBinding::bind)
    override val viewModel: MovieHomeViewModel by viewModels()

    override fun bindView() {

        viewModel.movieData.observe(this) {
            adapter.submitList(it)
        }
        viewModel.genres.observe(this) {
            genreAdapter.submitList(it.genres)
        }
    }

    override fun setUpView() {
        viewModel.getData()
        recyclerMovieHome.adapter = adapter
        recyclerMovieHome.isNestedScrollingEnabled = true
        recyclerGenres.adapter = genreAdapter
        recyclerGenres.isNestedScrollingEnabled = true
    }

    private fun onItemMovieClick(movieResult: MovieResult) {
        (activity as? AppCompatActivity)?.addFragment(DetailFragment.newInstance(movieResult),R.id.layoutParent)
    }
}
