package com.android.finalproject.ui.home.profilescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.android.finalproject.R
import com.android.finalproject.data.model.Movie
import com.android.finalproject.data.model.TvShow
import com.android.finalproject.databinding.FragmentProfileBinding
import com.android.finalproject.ui.base.BaseViewModelFragment
import com.android.finalproject.ui.home.moviescreen.HomeViewModel
import com.android.finalproject.ui.home.moviescreen.MovieAdapter
import com.android.finalproject.ui.home.moviescreen.MovieListResponseState
import com.android.finalproject.ui.home.seriesscreen.SeriesAdapter
import com.android.finalproject.ui.home.seriesscreen.SeriesListResponseState
import com.android.finalproject.util.Constants
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class ProfileFragment :
    BaseViewModelFragment<HomeViewModel, FragmentProfileBinding>(
        HomeViewModel::class
    ) {

    private val movieAdapter: ProfileMovieAdapter by lazy {
        ProfileMovieAdapter(context = requireContext())
    }

    private val seriesAdapter: ProfileSeriesAdapter by lazy {
        ProfileSeriesAdapter(context = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        super.initViews()
        viewModel.getFavSeries(Constants.SERIES_FAVORITE)
        viewModel.getFavMovie(Constants.MOVIE_FAVORITE)
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
                    movieAdapter.setData(it.movieList)
                    movieAdapter.onItemClick = { movie ->
                        showMovieDetailsScreen(movie)
                    }
                    binding.rvMovies.adapter = movieAdapter
                }
            }
        }.launchIn(lifecycleScope)

        viewModel.seriesListResponseState.onEach {
            when (it) {
                is SeriesListResponseState.Failure -> {
                    Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                }
                is SeriesListResponseState.Loading -> {
                    if (it.loading)
                        showDialog(getString(R.string.loading))
                    else
                        dismissDialog()
                }
                is SeriesListResponseState.Success -> {
                    seriesAdapter.setData(it.seriesList)
                    seriesAdapter.onItemClick = { series ->
                        showSeriesDetailsScreen(series)
                    }
                    binding.rvMovies.adapter = seriesAdapter

                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun showMovieDetailsScreen(movie: Movie) {

    }

    private fun showSeriesDetailsScreen(series: TvShow) {

    }
}