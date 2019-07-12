package com.rikkeisoft.moviedb.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.databinding.FragmentDetailScreenBinding
import com.rikkeisoft.moviedb.ui.base.BaseFragment
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeChildAdapter
import kotlinx.android.synthetic.main.fragment_detail_screen.recyclerCasting
import kotlinx.android.synthetic.main.fragment_detail_screen.recyclerSimilarMovie
import kotlinx.android.synthetic.main.fragment_detail_screen.toolbarDetail
import javax.inject.Inject
import javax.inject.Named

class DetailFragment : BaseFragment<FragmentDetailScreenBinding, DetailViewModel>() {
    override val layoutId: Int = R.layout.fragment_detail_screen
    private val castAdapter by lazy { DetailCastAdapter(mutableListOf()) }
    private val similarAdapter by lazy {
        MovieHomeChildAdapter(mutableListOf()) { movieResult ->
            onItemMovieClickListener(
                movieResult
            )
        }
    }

    @Inject
    @field:Named(DetailViewModel.NAME)
    lateinit var detailViewModel: DetailViewModel

    override fun initViewModel() {
        if (::detailViewModel.isInitialized) {
            viewModel = detailViewModel
        }
    }

    override fun initComponents() {
        showBackButton()
        val data = arguments?.getParcelable<MovieResult>(ARGUMENT_MOVIE)
        data?.let {
            viewModel.setDetailMovie(it)
            viewModel.getDetail(it.idMovie)
        }
        recyclerCasting.adapter = castAdapter
        recyclerSimilarMovie.adapter = similarAdapter
    }

    private fun showBackButton() {
        toolbarDetail.run {
            setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    override fun doObserve() {
        super.doObserve()
        viewModel.casters.observe(this, Observer {
            castAdapter.setData(it)
        })
        viewModel.similarMovies.observe(this, Observer {
            similarAdapter.setData(it)
        })
    }

    private fun onItemMovieClickListener(movieResult: MovieResult) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.layoutParent, newInstance(movieResult))
            ?.addToBackStack(null)
            ?.commit()
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