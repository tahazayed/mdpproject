package com.android.finalproject.ui.home.seriesscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

        val sprAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                arrayOf("Most Pop", "Top Rated", "New Release", "Favorite")
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
                        Toast.makeText(requireContext(), "0", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        Toast.makeText(requireContext(), "1", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        Toast.makeText(requireContext(), "2", Toast.LENGTH_SHORT).show()
                    }
                    3 -> {
                        Toast.makeText(requireContext(), "3", Toast.LENGTH_SHORT).show()
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
                    adapter.setData(it.response.results, it.favList)
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

    }

}