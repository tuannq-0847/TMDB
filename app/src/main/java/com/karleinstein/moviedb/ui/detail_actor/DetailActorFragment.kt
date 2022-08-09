package com.karleinstein.moviedb.ui.detail_actor

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.karleinstein.basemvvm.base.BaseFragment
import com.karleinstein.basemvvm.utils.viewBinding
import com.karleinstein.moviedb.R
import com.karleinstein.moviedb.data.model.MovieResult
import com.karleinstein.moviedb.data.model.SearchResult
import com.karleinstein.moviedb.databinding.FragmentDetailActorBinding
import com.karleinstein.moviedb.ui.detail.DetailFragment
import com.karleinstein.moviedb.ui.moviehome.MovieHomeChildAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail_actor.recyclerMovie
import kotlinx.android.synthetic.main.fragment_detail_actor.toolbarDetailActor
@AndroidEntryPoint
class DetailActorFragment : BaseFragment(R.layout.fragment_detail_actor) {

    private val adapter by lazy {
        MovieHomeChildAdapter() { movieResult ->
            onItemMovieClick(
                movieResult
            )
        }
    }

    private fun showBackButton() {
        toolbarDetailActor.run {
            setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun onItemMovieClick(movieResult: MovieResult) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.layoutParent, DetailFragment.newInstance(movieResult))
            ?.addToBackStack(null)
            ?.commit()
    }

    companion object {
        private const val ARGUMENT_ACTOR = "ARGUMENT_ACTOR"

        @JvmStatic
        fun newInstance(searchResult: SearchResult) = DetailActorFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARGUMENT_ACTOR, searchResult)
            }
        }
    }

    override val viewModel: DetailActorViewModel by viewModels()

    override val viewBinding: FragmentDetailActorBinding? by viewBinding(FragmentDetailActorBinding::bind)

    override fun bindView() {
        viewModel.movies.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun setUpView() {
        showBackButton()
        val data = arguments?.getParcelable<SearchResult>(ARGUMENT_ACTOR)
        data?.let {
            viewModel.getDetailActor(it.idMovie)
            viewModel.getMovieCredit(it.idMovie)
        }
        recyclerMovie.adapter = adapter
        recyclerMovie.isNestedScrollingEnabled = true
    }
}
