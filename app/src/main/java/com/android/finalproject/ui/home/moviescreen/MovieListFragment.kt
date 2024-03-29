package com.android.finalproject.ui.home.moviescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.android.finalproject.R
import com.android.finalproject.data.model.Movie
import com.android.finalproject.databinding.FragmentMovieListBinding
import com.android.finalproject.ui.base.BaseViewModelFragment
import com.android.finalproject.util.Constants
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MovieListFragment :
    BaseViewModelFragment<HomeViewModel, FragmentMovieListBinding>(
        HomeViewModel::class
    ) {
    private var position = 0
    private var searched = false
    private val adapter: MovieAdapter by lazy {
        MovieAdapter(context = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(layoutInflater)
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

        binding.ivSearch.setOnClickListener {
            val query = binding.etQuery.text.toString()
            if (query.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.write__query),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            searched = true
            viewModel.searchForMovies(query)
        }



        binding.etQuery.doOnTextChanged { text, _, _, _ ->
            if (text.toString().isEmpty() && searched) {
                fetchMovies(position)
                searched = false
            }
        }

        binding.sprSortBy.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                fetchMovies(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
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
                    adapter.setData(it.movieList, it.favList)
                    adapter.onItemClick = { movie ->
                        showMovieDetailsScreen(movie)
                    }
                    adapter.onItemFavClick = { favMovie, addToFav ->
                        viewModel.updateFavItem(favMovie, addToFav)
                    }
                    binding.rvMovies.adapter = adapter
                }
            }
        }.launchIn(lifecycleScope)

    }

    private fun fetchMovies(position: Int) {
        this.position = position

        when (this.position) {
            0 -> {
                viewModel.getDiscoverMovie(Constants.MOVIE_MOST_POPULAR)
            }

            1 -> {
                viewModel.getDiscoverMovie(Constants.MOVIE_TOP_RATED)
            }

            2 -> {
                viewModel.getDiscoverMovie(Constants.MOVIE_COMING_SOON)
            }

            3 -> {
                viewModel.getFavMovie(Constants.MOVIE_FAVORITE)
            }
        }
    }

    private fun showMovieDetailsScreen(movie: Movie) {
        findNavController().navigate(
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(
                movie
            )
        )
    }
}