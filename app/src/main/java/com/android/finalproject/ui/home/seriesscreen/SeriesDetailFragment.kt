package com.android.finalproject.ui.home.seriesscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.android.finalproject.data.model.TvShow
import com.android.finalproject.databinding.FragmentSeriesDetailBinding
import com.android.finalproject.ui.base.BaseViewModelFragment
import com.android.finalproject.ui.home.moviescreen.HomeViewModel


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
    }


}