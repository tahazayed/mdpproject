package com.android.finalproject.ui.home.moviescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.android.finalproject.R
import com.android.finalproject.data.model.Movie
import com.android.finalproject.databinding.FragmentMovieListBinding
import com.android.finalproject.ui.base.BaseViewModelFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MovieListFragment :
    BaseViewModelFragment<HomeViewModel, FragmentMovieListBinding>(
        HomeViewModel::class
    ) {

    private val adapter: MovieAdapter by lazy {
        MovieAdapter(context = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        super.initViews()
        viewModel.getDiscoverMovie()
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.movieListResponseState.onEach {
            when (it) {
                is MovieListResponseState.Failure -> {
                    Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                }
                is MovieListResponseState.Loading -> {
                    if (it.loading)
                        showDialog(getString(R.string.loading))
                    else
                        dismissDialog()
                }
                is MovieListResponseState.Success -> {
                    adapter.setData(it.response.results)
                    adapter.onItemClick = { movie ->
                        showMovieDetailsScreen(movie)
                    }
                    binding.rvMovies.adapter = adapter
                }
            }
        }.launchIn(lifecycleScope)

    }

    private fun showMovieDetailsScreen(movie: Movie) {

    }
}