package com.rikkeisoft.moviedb.ui.detail_actor

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.data.model.SearchResult
import com.rikkeisoft.moviedb.databinding.FragmentDetailActorBinding
import com.rikkeisoft.moviedb.ui.base.BaseFragment
import com.rikkeisoft.moviedb.ui.detail.DetailFragment
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeChildAdapter
import kotlinx.android.synthetic.main.fragment_detail_actor.recyclerMovie
import kotlinx.android.synthetic.main.fragment_detail_actor.toolbarDetailActor
import javax.inject.Inject

class DetailActorFragment : BaseFragment<FragmentDetailActorBinding, DetailActorViewModel>() {

    private val adapter by lazy {
        MovieHomeChildAdapter(mutableListOf()) { movieResult, view, position ->
            onItemMovieClick(movieResult, view, position)
        }
    }

    @Inject
    lateinit var actorViewModel: DetailActorViewModel

    override fun initViewModel() {
        if (::actorViewModel.isInitialized) {
            viewModel = actorViewModel
        }
    }

    override fun initComponents() {
        showBackButton()
        val data = arguments?.getParcelable<SearchResult>(ARGUMENT_ACTOR)
        data?.let {
            viewModel.getDetailActor(it.idMovie)
            viewModel.getMovieCredit(it.idMovie)
        }
        recyclerMovie.adapter = adapter
        recyclerMovie.isNestedScrollingEnabled = true
    }

    override val layoutId: Int = R.layout.fragment_detail_actor

    private fun showBackButton() {
        toolbarDetailActor.run {
            setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    override fun doObserve() {
        super.doObserve()
        viewModel.movies.observe(this, Observer {
            adapter.setData(it)
        })
    }

    private fun onItemMovieClick(movieResult: MovieResult, view: View, position: Int) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.layoutParent, DetailFragment.newInstance(movieResult,position))
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
}
