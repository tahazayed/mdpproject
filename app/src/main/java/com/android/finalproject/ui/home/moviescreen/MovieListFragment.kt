package com.android.finalproject.ui.home.moviescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.finalproject.databinding.FragmentMovieListBinding
import com.android.finalproject.ui.base.BaseViewModelFragment


class MovieListFragment :
    BaseViewModelFragment<HomeViewModel, FragmentMovieListBinding>(
        HomeViewModel::class
    ) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        super.initViews()
    }

    override fun initObservers() {
        super.initObservers()


    }
}