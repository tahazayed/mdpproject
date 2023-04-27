package com.android.finalproject.ui.home.seriesscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.android.finalproject.R
import com.android.finalproject.data.model.TvShow
import com.android.finalproject.databinding.FragmentSeriesDetailBinding
import com.android.finalproject.ui.base.BaseViewModelFragment
import com.android.finalproject.ui.home.moviescreen.HomeViewModel
import com.android.finalproject.ui.home.moviescreen.MovieVideoResponseState
import com.android.finalproject.util.Constants
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.onEach


class SeriesDetailFragment :
    BaseViewModelFragment<HomeViewModel, FragmentSeriesDetailBinding>(
        HomeViewModel::class
    ) {

    private val navArg: SeriesDetailFragmentArgs by navArgs()
    private lateinit var series: TvShow

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeriesDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        super.initViews()

        series = navArg.series
        viewModel.getSeriesVideos(series.id)

        fetishMovieIntoUi()
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.seriesVideoResponseState.onEach {
            when(it){
                is SeriesVideoResponseState.Failure -> {

                }
                is SeriesVideoResponseState.Success -> {

                }
            }
        }
    }

    private fun fetishMovieIntoUi(){
        setTitle()
        setLanguage()
        setMovieReleaseDate()
        setAverageVote()
        setPopularity()
        setOverview()
        setPicture()
    }

    private fun setTitle() {
        val title = series.original_name;
        val titleTextView = binding.movieTitle;
        titleTextView.text = title;
    }

    private fun setLanguage() {
        val movieLanguage = series.original_language;
        val movieLanguageTextView = binding.originalLanguage;
        movieLanguageTextView.text = movieLanguage;
    }

    private fun setMovieReleaseDate() {
        val releaseDate = series.first_air_date;
        val releaseDateTextView = binding.movieReleaseDate;
        releaseDateTextView.text = releaseDate
    }

    private fun setAverageVote() {
        val averageVote = series.vote_average;
        val averageVoteTextView = binding.movieVoteAverage;
        averageVoteTextView.text = averageVote?.toString();
    }

    private fun setPopularity() {
        val popularity = series.popularity;
        val popularityTextView = binding.moviePopularityValue
        popularityTextView.text = popularity?.toString();
    }

    private fun setOverview() {
        val overview = series.overview;
        val overviewTextView = binding.movieOverview;
        overviewTextView.text = overview;
    }

    private fun setPicture() {
        val moviePicture = series.poster_path;

        Glide.with(requireContext())
            .load(Constants.POSTER_PATH_PRE_URL + moviePicture)
            .placeholder(R.drawable.ic_movie)
            .centerCrop()
            .into(binding.moviePoster)
    }


}