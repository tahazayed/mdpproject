package com.android.finalproject.ui.home.profilescreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.finalproject.R
import com.android.finalproject.data.model.Movie
import com.android.finalproject.databinding.ProfileFavItemBinding
import com.android.finalproject.util.Constants
import com.bumptech.glide.Glide

class ProfileMovieAdapter(
    private var dataSet: List<Movie> = arrayListOf(),
    private val context: Context
) : RecyclerView.Adapter<ProfileMovieAdapter.ViewHolder>() {

    lateinit var onItemClick: (Movie) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ProfileFavItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = dataSet[position]

        holder.binding.tvTitle.text = movie.title

        Glide.with(context)
            .load(Constants.POSTER_PATH_PRE_URL + movie.poster_path)
            .placeholder(R.drawable.ic_movie)
            .centerCrop()
            .into(holder.binding.ivPoster)


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

    inner class ViewHolder(val binding: ProfileFavItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}