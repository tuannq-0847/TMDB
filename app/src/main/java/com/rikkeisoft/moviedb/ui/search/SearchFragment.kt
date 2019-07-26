package com.rikkeisoft.moviedb.ui.search

import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.data.model.SearchResult
import com.rikkeisoft.moviedb.databinding.FragmentSearchMovieBinding
import com.rikkeisoft.moviedb.ui.base.BaseFragment
import com.rikkeisoft.moviedb.ui.detail.DetailCastAdapter
import com.rikkeisoft.moviedb.ui.detail.DetailFragment
import com.rikkeisoft.moviedb.ui.detail_actor.DetailActorFragment
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeParentAdapter
import kotlinx.android.synthetic.main.fragment_search_movie.layoutEmptySearch
import kotlinx.android.synthetic.main.fragment_search_movie.recyclerActors
import kotlinx.android.synthetic.main.fragment_search_movie.recyclerSearchParent
import kotlinx.android.synthetic.main.fragment_search_movie.searchMovie
import kotlinx.android.synthetic.main.fragment_search_movie.textActor
import javax.inject.Inject

class SearchFragment : BaseFragment<FragmentSearchMovieBinding, SearchViewModel>(), OnQueryTextListener,
    OnTouchListener {

    override val layoutId: Int = R.layout.fragment_search_movie

    private val adapter by lazy {
        MovieHomeParentAdapter(mutableListOf()) { movieResult, view, position ->
            onItemMovieClick(
                movieResult, view, position
            )
        }
    }

    private val actorAdapter by lazy {
        DetailCastAdapter(mutableListOf()) { searchResult -> onItemCastClickListener(searchResult) }
    }

    @Inject
    lateinit var searchViewModel: SearchViewModel

    override fun initViewModel() {
        if (::searchViewModel.isInitialized) {
            viewModel = searchViewModel
        }
    }

    override fun initComponents() {
        val editText = searchMovie.findViewById<AutoCompleteTextView>(R.id.search_src_text)
        context?.let {
            editText.setHintTextColor(ContextCompat.getColor(it, android.R.color.black))
            editText.setTextColor(ContextCompat.getColor(it, android.R.color.black))
            searchMovie.setOnQueryTextListener(this)
        }
        recyclerSearchParent.adapter = adapter
        recyclerActors.adapter = actorAdapter
        view?.setOnTouchListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        handleEmpty(newText)
        newText?.toLowerCase()?.let {
            viewModel.getResult(it)
        }
        return true
    }

    override fun doObserve() {
        super.doObserve()
        viewModel.searchResults.observe(this, Observer {
            adapter.setData(it)
        })


        viewModel.castDetails.observe(this, Observer {
            actorAdapter.setData(it)
        })
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        //    hideKeyBoard()
        return true
    }

    private fun onItemCastClickListener(searchResult: SearchResult) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.layoutParent, DetailActorFragment.newInstance(searchResult))
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun handleEmpty(newText: String?) {
        layoutEmptySearch.visibility = if (newText.isNullOrEmpty()) View.VISIBLE else View.GONE
        recyclerSearchParent.visibility = if (newText.isNullOrEmpty()) View.GONE else View.VISIBLE
        recyclerActors.visibility = if (newText.isNullOrEmpty()) View.GONE else View.VISIBLE
        textActor.visibility = if (newText.isNullOrEmpty()) View.GONE else View.VISIBLE
    }

    private fun onItemMovieClick(movieResult: MovieResult, view: View, position: Int) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.layoutParent, DetailFragment.newInstance(movieResult, position))
            ?.addToBackStack(null)
            ?.commit()
    }
}
