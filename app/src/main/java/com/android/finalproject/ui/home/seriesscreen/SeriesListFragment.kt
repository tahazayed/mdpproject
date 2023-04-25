package com.android.finalproject.ui.home.seriesscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.android.finalproject.R
import com.android.finalproject.data.model.TvShow
import com.android.finalproject.databinding.FragmentSeriesListBinding
import com.android.finalproject.ui.base.BaseViewModelFragment
import com.android.finalproject.ui.home.moviescreen.HomeViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class SeriesListFragment :
    BaseViewModelFragment<HomeViewModel, FragmentSeriesListBinding>(
        HomeViewModel::class
    ) {

    private val adapter: SeriesAdapter by lazy {
        SeriesAdapter(context = requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeriesListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initViews() {
        super.initViews()
        viewModel.getDiscoverSeries()
    }

    override fun initObservers() {
        super.initObservers()

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
                    adapter.setData(it.response.results)
                    adapter.onItemClick = { series ->
                        showSeriesDetailsScreen(series)
                    }
                    binding.rvSeries.adapter = adapter
                }
            }
        }.launchIn(lifecycleScope)

    }

    private fun showSeriesDetailsScreen(series: TvShow) {

    }

}