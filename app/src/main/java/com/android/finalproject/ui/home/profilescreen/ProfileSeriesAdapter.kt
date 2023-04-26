package com.android.finalproject.ui.home.profilescreen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.finalproject.R
import com.android.finalproject.data.model.TvShow
import com.android.finalproject.databinding.ProfileFavItemBinding
import com.android.finalproject.util.Constants
import com.bumptech.glide.Glide

class ProfileSeriesAdapter (
    private var dataSet: List<TvShow> = arrayListOf(),
    private val context: Context
) : RecyclerView.Adapter<ProfileSeriesAdapter.ViewHolder>() {

    lateinit var onItemClick: (TvShow) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ProfileFavItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val series = dataSet[position]

        holder.binding.tvTitle.text = series.name

        Glide.with(context)
            .load(Constants.POSTER_PATH_PRE_URL + series.poster_path)
            .placeholder(R.drawable.ic_movie)
            .centerCrop()
            .into(holder.binding.ivPoster)


        holder.binding.root.setOnClickListener {
            onItemClick.invoke(series)
        }

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(dataSet: List<TvShow>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ProfileFavItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}