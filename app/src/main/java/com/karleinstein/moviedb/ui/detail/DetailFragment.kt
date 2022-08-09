package com.karleinstein.moviedb.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.karleinstein.basemvvm.base.BaseFragment
import com.karleinstein.basemvvm.extension.addFragment
import com.karleinstein.basemvvm.utils.viewBinding
import com.karleinstein.moviedb.R
import com.karleinstein.moviedb.data.model.MovieResult
import com.karleinstein.moviedb.data.model.SearchResult
import com.karleinstein.moviedb.databinding.FragmentDetailScreenBinding
import com.karleinstein.moviedb.ui.detail_actor.DetailActorFragment
import com.karleinstein.moviedb.ui.detail_actor.DetailActorViewModel
import com.karleinstein.moviedb.ui.moviehome.MovieHomeChildAdapter
import com.karleinstein.moviedb.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail_screen.fabAddFav
import kotlinx.android.synthetic.main.fragment_detail_screen.layoutEmptyMovie
import kotlinx.android.synthetic.main.fragment_detail_screen.nestedScrollDetail
import kotlinx.android.synthetic.main.fragment_detail_screen.recyclerCasting
import kotlinx.android.synthetic.main.fragment_detail_screen.recyclerSimilarMovie
import kotlinx.android.synthetic.main.fragment_detail_screen.toolbarDetail

@AndroidEntryPoint
class DetailFragment : BaseFragment(R.layout.fragment_detail_screen),
    NestedScrollView.OnScrollChangeListener {

    private val castAdapter by lazy {
        DetailCastAdapter() { searchResult ->
            onItemCastClickListener(
                searchResult
            )
        }
    }
    private val similarAdapter by lazy {
        MovieHomeChildAdapter() { movieResult ->
            onItemMovieClickListener(
                movieResult
            )
        }
    }

    private fun onItemMovieClickListener(movieResult: MovieResult) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.layoutParent, newInstance(movieResult))
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun onItemCastClickListener(searchResult: SearchResult) {
        (activity as? AppCompatActivity)?.addFragment(
            DetailActorFragment.newInstance(searchResult),
            R.id.layoutParent,
            true
        )
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

    override val viewBinding: ViewBinding? by viewBinding(FragmentDetailScreenBinding::bind)

    override val viewModel: DetailViewModel by viewModels()

    override fun bindView() {
        viewModel.casters.observe(this, Observer {
            castAdapter.submitList(it)
        })
        viewModel.similarMovies.observe(this, Observer {
            recyclerSimilarMovie.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
            layoutEmptyMovie.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            similarAdapter.submitList(it)
        })
        viewModel.favoriteResult.observe(this, Observer {
            if (it) {
                context?.showMessage(resources.getString(R.string.save_successfully))
            } else {
                context?.showMessage(resources.getString(R.string.remove_successfully))
            }
        })
    }

    override fun setUpView() {
        showBackButton()
        nestedScrollDetail.setOnScrollChangeListener(this)
        val data = arguments?.getParcelable<MovieResult>(ARGUMENT_MOVIE)
        data?.let {
            viewModel.getDetail(it)
        }
        recyclerCasting.adapter = castAdapter
        recyclerCasting.isNestedScrollingEnabled = true
        recyclerSimilarMovie.adapter = similarAdapter
        recyclerSimilarMovie.isNestedScrollingEnabled = true
    }

    override fun onScrollChange(
        v: NestedScrollView,
        scrollX: Int,
        scrollY: Int,
        oldScrollX: Int,
        oldScrollY: Int
    ) {
        if (scrollY > oldScrollY) {
            fabAddFav.hide()
        } else {
            fabAddFav.show()
        }
    }
}
