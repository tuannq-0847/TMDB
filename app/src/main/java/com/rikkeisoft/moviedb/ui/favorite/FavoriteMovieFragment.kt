package com.rikkeisoft.moviedb.ui.favorite

import android.view.View
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.rikkeisoft.moviedb.R
import com.rikkeisoft.moviedb.data.model.MovieResult
import com.rikkeisoft.moviedb.databinding.FragmentFavoriteBinding
import com.rikkeisoft.moviedb.ui.base.BaseFragment
import com.rikkeisoft.moviedb.ui.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_favorite.layoutEmptyFavorite
import kotlinx.android.synthetic.main.fragment_favorite.recyclerFavorite
import kotlinx.android.synthetic.main.fragment_favorite.swipeRefresh
import javax.inject.Inject
import javax.inject.Named

class FavoriteMovieFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>(), OnRefreshListener {
    override val layoutId: Int = R.layout.fragment_favorite
    private val adapter by lazy {
        FavoriteMovieAdapter(mutableListOf()) { movieResult, position ->
            onItemFavoriteClick(movieResult, position)
        }
    }

    @Inject
    @field:Named(FavoriteViewModel.NAME)
    lateinit var favoriteViewModel: FavoriteViewModel

    override fun initViewModel() {
        if (::favoriteViewModel.isInitialized) {
            viewModel = favoriteViewModel
        }
    }

    override fun initComponents() {
        viewModel.getAllFavorites()
        recyclerFavorite.adapter = adapter
        swipeRefresh.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        viewModel.getAllFavorites()
        swipeRefresh.isRefreshing = false
    }

    override fun doObserve() {
        super.doObserve()
        viewModel.favorites.observe(this, Observer {
            recyclerFavorite.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
            layoutEmptyFavorite.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            adapter.setData(it)
        })
    }

    private fun onItemFavoriteClick(movieResult: MovieResult, position: Int) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.layoutParent, DetailFragment.newInstance(movieResult, position))
            ?.addToBackStack(null)
            ?.commit()
    }
}
