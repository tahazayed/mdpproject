package com.android.finalproject.ui.home.seriesscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.finalproject.R
import com.android.finalproject.data.model.TvShow
import com.android.finalproject.databinding.FragmentSeriesListBinding
import com.android.finalproject.ui.base.BaseViewModelFragment
import com.android.finalproject.ui.home.moviescreen.HomeViewModel
import com.android.finalproject.util.Constants
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

        val sprAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                resources.getStringArray(R.array.sort)
            )

        binding.sprSortBy.adapter = sprAdapter

        binding.sprSortBy.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        viewModel.getDiscoverSeries(Constants.SERIES_MOST_POPULAR)
                    }
                    1 -> {
                        viewModel.getDiscoverSeries(Constants.SERIES_TOP_RATED)
                    }
                    2 -> {
                        viewModel.getDiscoverSeries(Constants.SERIES_COMING_SOON)
                    }
                    3 -> {
                        viewModel.getFavSeries(Constants.SERIES_FAVORITE)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
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
                    adapter.setData(it.seriesList, it.favList)
                    adapter.onItemClick = { series ->
                        showSeriesDetailsScreen(series)
                    }
                    adapter.onItemFavClick = { favSeries, addToFav ->
                        viewModel.updateFavItem(favSeries, addToFav)
                    }
                    binding.rvSeries.adapter = adapter
                }
            }
        }.launchIn(lifecycleScope)

    }

    private fun showSeriesDetailsScreen(series: TvShow) {
        findNavController().navigate(
            SeriesListFragmentDirections.actionSeriesListFragmentToSeriesDetailFragment(
                series
            )
        )
    }

}