package com.android.finalproject.ui.home.profilescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.finalproject.databinding.FragmentProfileBinding
import com.android.finalproject.ui.base.BaseViewModelFragment
import com.android.finalproject.ui.home.moviescreen.HomeViewModel


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


}