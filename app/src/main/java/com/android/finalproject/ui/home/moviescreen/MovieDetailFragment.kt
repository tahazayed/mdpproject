package com.android.finalproject.ui.home.moviescreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.android.finalproject.R
import com.android.finalproject.data.model.Movie
import com.android.finalproject.data.model.VideosList
import com.android.finalproject.databinding.FragmentMovieDetailBinding
import com.android.finalproject.ui.base.BaseViewModelFragment
import com.android.finalproject.util.Constants
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MovieDetailFragment :
    BaseViewModelFragment<HomeViewModel, FragmentMovieDetailBinding>(
        HomeViewModel::class
    ) {
    //https://www.youtube.com/watch?v=ThseAPVAtMQ

    private lateinit var videoId: VideosList
    private val navArg: MovieDetailFragmentArgs by navArgs()
    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        super.initViews()

        movie = navArg.movie
        viewModel.getMovieVideos(movie.id)

        fetchMovieIntoUi()

        binding.watchTrailerBtn.setOnClickListener {
            videoId.results.let {
                if (it?.size!! > 0) {
                    it?.get(0)?.key?.let { key ->
                        openYoutubeIntent(key)
                    } ?: run {
                        Toast.makeText(requireContext(), "No videos available!", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    Toast.makeText(requireContext(), "No videos available!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.movieVideoResponseState.onEach {
            when (it) {
                is MovieVideoResponseState.Failure -> {
                }

                is MovieVideoResponseState.Success -> {
                    videoId = it.movieList
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun fetchMovieIntoUi() {
        setTitle()
        adultContentText()
        setLanguage()
        setMovieReleaseDate()
        setAverageVote()
        setPopularity()
        setOverview()
        setPicture()
    }

    private fun setTitle() {
        val title = movie.title;
        val titleTextView = binding.movieTitle;
        titleTextView.text = title;
    }

    private fun adultContentText() {
        val adultContent = movie.adult
        val adultContentViewText = binding.movieCategoryValue;
        if (adultContent) {
            adultContentViewText.text = "Adult"
        } else {
            adultContentViewText.text = "Family Friendly"
        }
    }

    private fun setLanguage() {
        val movieLanguage = movie.original_language;
        val movieLanguageTextView = binding.originalLanguage;
        movieLanguageTextView.text = movieLanguage?.uppercase();
    }

    private fun setMovieReleaseDate() {
        val releaseDate = movie.release_date;
        val releaseDateTextView = binding.movieReleaseDate;
        releaseDateTextView.text = releaseDate
    }

    private fun setAverageVote() {
        val averageVote = movie.vote_average;
        val averageVoteTextView = binding.movieVoteAverage;
        averageVoteTextView.text = averageVote?.toString();
    }

    private fun setPopularity() {
        val popularity = movie.popularity;
        val popularityTextView = binding.moviePopularityValue
        popularityTextView.text = popularity?.toString();
    }

    private fun setOverview() {
        val overview = movie.overview;
        val overviewTextView = binding.movieOverview;
        overviewTextView.text = overview;
    }

    private fun setPicture() {
        val moviePicture = movie.poster_path;

        Glide.with(requireContext())
            .load(Constants.POSTER_PATH_PRE_URL + moviePicture)
            .placeholder(R.drawable.ic_movie)
            .centerCrop()
            .into(binding.moviePoster)
    }

    private fun watchTrailerBn() {
        binding.watchTrailerBtn.setOnClickListener {
        }
    }

    private fun openYoutubeIntent(videoId: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
        intent.putExtra("VIDEO_ID", videoId)
        startActivity(intent)
    }

}