package com.android.finalproject.ui.home.moviescreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.android.finalproject.R
import com.android.finalproject.data.model.Movie
import com.android.finalproject.databinding.MovieItemBinding
import com.android.finalproject.util.Constants
import com.bumptech.glide.Glide

class MovieAdapter(
    private var dataSet: ArrayList<Movie> = arrayListOf(),
    private var favSet: ArrayList<Movie> = arrayListOf(),
    private val context: Context
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    lateinit var onItemClick: (Movie) -> Unit
    lateinit var onItemFavClick: (Movie, Boolean) -> Unit

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

        if (favSet.contains(movie))
            holder.binding.ivAddToFav.background =
                AppCompatResources.getDrawable(context, R.drawable.ic_added_to_fav)
        else
            holder.binding.ivAddToFav.background =
                AppCompatResources.getDrawable(context, R.drawable.ic_removed_from_fav)


        holder.binding.llMovieDetails.setOnClickListener {
            onItemClick.invoke(movie)
        }

        holder.binding.ivMoviePoster.setOnClickListener {
            onItemClick.invoke(movie)
        }

        holder.binding.ivAddToFav.setOnClickListener {
            if (favSet.contains(movie)) {
                onItemFavClick.invoke(movie, false)
                holder.binding.ivAddToFav.background =
                    AppCompatResources.getDrawable(context, R.drawable.ic_removed_from_fav)
                favSet.remove(movie)

            } else {
                onItemFavClick.invoke(movie, true)
                holder.binding.ivAddToFav.background =
                    AppCompatResources.getDrawable(context, R.drawable.ic_added_to_fav)
                favSet.add(movie)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(dataSet: List<Movie>, favSet: List<Movie>) {
        this.dataSet = dataSet as ArrayList<Movie>
        this.favSet = favSet as ArrayList<Movie>

        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}