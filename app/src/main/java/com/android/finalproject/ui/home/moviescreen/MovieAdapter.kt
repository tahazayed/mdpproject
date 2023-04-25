package com.android.finalproject.ui.home.moviescreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.finalproject.R
import com.android.finalproject.data.model.Movie
import com.android.finalproject.databinding.MovieItemBinding
import com.android.finalproject.util.Constants
import com.bumptech.glide.Glide

class MovieAdapter(
    private var dataSet: List<Movie> = arrayListOf(), private val context: Context
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    lateinit var onItemClick: (Movie) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = dataSet[position]

        holder.binding.tvTitle.text = movie.title
        holder.binding.tvReleaseDate.text = movie.release_date
        holder.binding.tvRating.text = movie.vote_average.toString()

        Glide.with(context)
            .load(Constants.POSTER_PATH_PRE_URL + movie.poster_path)
            .placeholder(R.drawable.ic_movie)
            .centerCrop()
            .into(holder.binding.ivMoviePoster)

        holder.binding.root.setOnClickListener {
            onItemClick.invoke(movie)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(dataSet: List<Movie>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}