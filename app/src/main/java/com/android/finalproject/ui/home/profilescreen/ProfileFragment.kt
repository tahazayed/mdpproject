package com.android.finalproject.ui.home.profilescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.android.finalproject.R
import com.android.finalproject.databinding.FragmentProfileBinding
import com.android.finalproject.ui.base.BaseViewModelFragment
import com.android.finalproject.ui.home.moviescreen.HomeViewModel
import com.android.finalproject.ui.home.moviescreen.MovieListResponseState
import com.android.finalproject.ui.home.seriesscreen.SeriesListResponseState
import com.android.finalproject.util.Constants
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class ProfileFragment :
    BaseViewModelFragment<HomeViewModel, FragmentProfileBinding>(
        HomeViewModel::class
    ) {
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
        viewModel.getFavMovie(Constants.SERIES_FAVORITE)
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
                    Toast.makeText(requireContext(), "movies fetched", Toast.LENGTH_SHORT).show()

//                    adapter.setData(it.movieList, it.favList)
//                    adapter.onItemClick = { movie ->
//                        showMovieDetailsScreen(movie)
//                    }
//                    binding.rvMovies.adapter = adapter
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
                    Toast.makeText(requireContext(), "tv shows fetched", Toast.LENGTH_SHORT).show()
//                    adapter.setData(it.seriesList, it.favList)
//                    adapter.onItemClick = { series ->
//                        showSeriesDetailsScreen(series)
//                    }
//                    binding.rvSeries.adapter = adapter
                }
            }
        }.launchIn(lifecycleScope)
    }
}