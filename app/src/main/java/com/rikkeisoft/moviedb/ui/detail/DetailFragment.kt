package com.rikkeisoft.moviedb.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.data.model.SearchResult
import com.rikkeisoft.moviedb.databinding.FragmentDetailScreenBinding
import com.rikkeisoft.moviedb.ui.base.BaseFragment
import com.rikkeisoft.moviedb.ui.detail_actor.DetailActorFragment
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeChildAdapter
import com.rikkeisoft.moviedb.utils.showMessage
import kotlinx.android.synthetic.main.fragment_detail_screen.fabAddFav
import kotlinx.android.synthetic.main.fragment_detail_screen.layoutEmptyMovie
import kotlinx.android.synthetic.main.fragment_detail_screen.nestedScrollDetail
import kotlinx.android.synthetic.main.fragment_detail_screen.recyclerCasting
import kotlinx.android.synthetic.main.fragment_detail_screen.recyclerSimilarMovie
import kotlinx.android.synthetic.main.fragment_detail_screen.toolbarDetail
import javax.inject.Inject
import javax.inject.Named

class DetailFragment : BaseFragment<FragmentDetailScreenBinding, DetailViewModel>(),
    NestedScrollView.OnScrollChangeListener {

    override val layoutId: Int = R.layout.fragment_detail_screen
    private val castAdapter by lazy {
        DetailCastAdapter(mutableListOf()) { searchResult ->
            onItemCastClickListener(
                searchResult
            )
        }
    }
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

    override fun onScrollChange(v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        if (scrollY > oldScrollY) {
            fabAddFav.hide()
        } else {
            fabAddFav.show()
        }
    }

    override fun initComponents() {
        showBackButton()
        nestedScrollDetail.setOnScrollChangeListener(this)
        val data = arguments?.getParcelable<MovieResult>(ARGUMENT_MOVIE)
        data?.let {
            viewModel.getDetail(it)
        }
        recyclerCasting.adapter = castAdapter
        recyclerSimilarMovie.adapter = similarAdapter
    }

    override fun doObserve() {
        super.doObserve()
        viewModel.casters.observe(this, Observer {
            castAdapter.setData(it)
        })
        viewModel.similarMovies.observe(this, Observer {
            recyclerSimilarMovie.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
            layoutEmptyMovie.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            similarAdapter.setData(it)
        })
        viewModel.favoriteResult.observe(this, Observer {
            if (it) {
                context?.showMessage(resources.getString(R.string.save_successfully))
            } else {
                context?.showMessage(resources.getString(R.string.remove_successfully))
            }
        })
    }

    private fun onItemMovieClickListener(movieResult: MovieResult) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.layoutParent, newInstance(movieResult))
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun onItemCastClickListener(searchResult: SearchResult) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.layoutParent, DetailActorFragment.newInstance(searchResult))
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun showBackButton() {
        toolbarDetail.run {
            setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
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
