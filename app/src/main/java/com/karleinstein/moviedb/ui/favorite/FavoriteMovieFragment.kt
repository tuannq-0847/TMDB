//package com.karleinstein.moviedb.ui.favorite
//
//import android.view.View
//import androidx.lifecycle.Observer
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
//import com.karleinstein.basemvvm.base.BaseFragment
//import com.karleinstein.moviedb.R
//import com.karleinstein.moviedb.data.model.MovieResult
//import com.karleinstein.moviedb.databinding.FragmentFavoriteBinding
//import com.karleinstein.moviedb.ui.base.BaseFragment
//import com.karleinstein.moviedb.ui.detail.DetailFragment
//import kotlinx.android.synthetic.main.fragment_favorite.layoutEmptyFavorite
//import kotlinx.android.synthetic.main.fragment_favorite.recyclerFavorite
//import kotlinx.android.synthetic.main.fragment_favorite.swipeRefresh
//import javax.inject.Inject
//import javax.inject.Named
//
//class FavoriteMovieFragment : BaseFragment(R.layout.fragment_favorite), OnRefreshListener {
//    private val adapter by lazy {
//        FavoriteMovieAdapter() { movieResult ->
//            onItemFavoriteClick(movieResult)
//        }
//    }
//
//    @Inject
//    @field:Named(FavoriteViewModel.NAME)
//    lateinit var favoriteViewModel: FavoriteViewModel
//
//    override fun initViewModel() {
//        if (::favoriteViewModel.isInitialized) {
//            viewModel = favoriteViewModel
//        }
//    }
//
//    override fun initComponents() {
//        viewModel.getAllFavorites()
//        recyclerFavorite.adapter = adapter
//        swipeRefresh.setOnRefreshListener(this)
//    }
//
//    override fun onRefresh() {
//        viewModel.getAllFavorites()
//        swipeRefresh.isRefreshing = false
//    }
//
//    override fun doObserve() {
//        super.doObserve()
//        viewModel.favorites.observe(this, Observer {
//            recyclerFavorite.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
//            layoutEmptyFavorite.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
//            adapter.setData(it)
//        })
//    }
//
//    private fun onItemFavoriteClick(movieResult: MovieResult) {
//        activity?.supportFragmentManager?.beginTransaction()
//            ?.add(R.id.layoutParent, DetailFragment.newInstance(movieResult))
//            ?.addToBackStack(null)
//            ?.commit()
//    }
//}
