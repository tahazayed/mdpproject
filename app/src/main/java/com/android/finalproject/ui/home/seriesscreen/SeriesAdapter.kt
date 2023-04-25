package com.android.finalproject.ui.home.seriesscreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.finalproject.R
import com.android.finalproject.data.model.TvShow
import com.android.finalproject.databinding.SeriesItemBinding
import com.android.finalproject.util.Constants
import com.bumptech.glide.Glide

class SeriesAdapter(
    private var dataSet: List<TvShow> = arrayListOf(), private val context: Context
) : RecyclerView.Adapter<SeriesAdapter.ViewHolder>() {

    lateinit var onItemClick: (TvShow) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SeriesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = dataSet[position]

        holder.binding.tvTitle.text = tvShow.name
        holder.binding.tvReleaseDate.text = tvShow.first_air_date
        holder.binding.tvRating.text = tvShow.vote_average.toString()

        Glide.with(context)
            .load(Constants.POSTER_PATH_PRE_URL + tvShow.poster_path)
            .placeholder(R.drawable.ic_movie)
            .centerCrop()
            .into(holder.binding.ivMoviePoster)

        holder.binding.root.setOnClickListener {
            onItemClick.invoke(tvShow)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(dataSet: List<TvShow>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: SeriesItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}