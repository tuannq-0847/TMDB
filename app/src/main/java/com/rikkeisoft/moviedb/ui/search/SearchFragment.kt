package com.rikkeisoft.moviedb.ui.search

import android.view.View
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.databinding.FragmentSearchMovieBinding
import com.rikkeisoft.moviedb.ui.base.BaseFragment
import com.rikkeisoft.moviedb.ui.detail.DetailCastAdapter
import com.rikkeisoft.moviedb.ui.moviehome.MovieHomeParentAdapter
import kotlinx.android.synthetic.main.fragment_search_movie.layoutEmptySearch
import kotlinx.android.synthetic.main.fragment_search_movie.recyclerActors
import kotlinx.android.synthetic.main.fragment_search_movie.recyclerSearchParent
import kotlinx.android.synthetic.main.fragment_search_movie.searchMovie
import kotlinx.android.synthetic.main.fragment_search_movie.textActor
import javax.inject.Inject

class SearchFragment : BaseFragment<FragmentSearchMovieBinding, SearchViewModel>(), OnQueryTextListener {
    override val layoutId: Int = R.layout.fragment_search_movie

    private val adapter by lazy {
        MovieHomeParentAdapter(mutableListOf()) { movieResult ->
            onItemMovieClick(
                movieResult
            )
        }
    }

    private val actorAdapter by lazy {
        DetailCastAdapter(mutableListOf())
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
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        layoutEmptySearch.visibility = if (newText.isNullOrEmpty()) View.VISIBLE else View.GONE
        recyclerSearchParent.visibility = if (newText.isNullOrEmpty()) View.GONE else View.VISIBLE
        recyclerActors.visibility = if (newText.isNullOrEmpty()) View.GONE else View.VISIBLE
        textActor.visibility = if (newText.isNullOrEmpty()) View.GONE else View.VISIBLE
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

    private fun onItemMovieClick(movieResult: MovieResult) {
    }
}
