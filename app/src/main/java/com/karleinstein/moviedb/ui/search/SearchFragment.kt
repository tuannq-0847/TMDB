package com.karleinstein.moviedb.ui.search

import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.karleinstein.basemvvm.base.BaseFragment
import com.karleinstein.basemvvm.utils.viewBinding
import com.karleinstein.moviedb.R
import com.karleinstein.moviedb.data.model.MovieResult
import com.karleinstein.moviedb.data.model.SearchResult
import com.karleinstein.moviedb.databinding.FragmentSearchMovieBinding
import com.karleinstein.moviedb.ui.detail.DetailCastAdapter
import com.karleinstein.moviedb.ui.detail.DetailFragment
import com.karleinstein.moviedb.ui.detail_actor.DetailActorFragment
import com.karleinstein.moviedb.ui.moviehome.MovieHomeParentAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search_movie.layoutEmptySearch
import kotlinx.android.synthetic.main.fragment_search_movie.recyclerActors
import kotlinx.android.synthetic.main.fragment_search_movie.recyclerSearchParent
import kotlinx.android.synthetic.main.fragment_search_movie.searchMovie
import kotlinx.android.synthetic.main.fragment_search_movie.textActor

@AndroidEntryPoint
class SearchFragment : BaseFragment(R.layout.fragment_search_movie), OnQueryTextListener,
    OnTouchListener {

    private val adapter by lazy {
        MovieHomeParentAdapter { movieResult ->
            onItemMovieClick(
                movieResult
            )
        }
    }

    private val actorAdapter by lazy {
        DetailCastAdapter { searchResult -> onItemCastClickListener(searchResult) }
    }

    override val viewModel: SearchViewModel by viewModels()
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

    private fun onItemMovieClick(movieResult: MovieResult) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.layoutParent, DetailFragment.newInstance(movieResult))
            ?.addToBackStack(null)
            ?.commit()
    }

    override val viewBinding: FragmentSearchMovieBinding? by viewBinding(FragmentSearchMovieBinding::bind)

    override fun bindView() {
        viewModel.searchResults.observe(this, Observer {
            adapter.submitList(it)
        })


        viewModel.castDetails.observe(this, Observer {
            actorAdapter.submitList(it)
        })
    }

    override fun setUpView() {
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
}
