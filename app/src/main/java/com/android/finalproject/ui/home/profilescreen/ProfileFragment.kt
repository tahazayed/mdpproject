package com.android.finalproject.ui.home.profilescreen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.finalproject.R
import com.android.finalproject.data.SessionManager
import com.android.finalproject.data.model.Movie
import com.android.finalproject.data.model.TvShow
import com.android.finalproject.databinding.FragmentProfileBinding
import com.android.finalproject.ui.auth.AuthActivity
import com.android.finalproject.ui.base.BaseViewModelFragment
import com.android.finalproject.ui.home.moviescreen.HomeViewModel
import com.android.finalproject.ui.home.moviescreen.MovieListResponseState
import com.android.finalproject.ui.home.seriesscreen.SeriesListResponseState
import com.android.finalproject.util.Constants
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.math.log


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

        viewModel.getProfileInfo()
        viewModel.getFavSeries(Constants.SERIES_FAVORITE)
        viewModel.getFavMovie(Constants.MOVIE_FAVORITE)
    }

    override fun initObservers() {
        super.initObservers()

        viewModel.user.observe(this){
            binding.tvName.text = it.name
            binding.tvEmail.setText(it.email)
        }

        binding.btnSignOut.setOnClickListener {
            viewModel.signOut()
            logout()
        }

        binding.btnDeleteMyAccount.setOnClickListener {
            viewModel.deleteMyAccount()
            logout()
        }

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
                    binding.rvSeries.adapter = seriesAdapter

                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun showMovieDetailsScreen(movie: Movie) {
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToMovieDetailFragment(movie))
    }

    private fun showSeriesDetailsScreen(series: TvShow) {
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSeriesDetailFragment(series))
    }

    private fun logout(){
        val intent = Intent(requireContext(), AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}