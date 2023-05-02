package com.example.movieapp.presentation.ui.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.domain.MovieItem
import com.squareup.picasso.Picasso

class TopAdapter() :
    RecyclerView.Adapter<TopAdapter.TopMovieInfoViewHolder>() {

    var topMovieInfoList: List<MovieItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMovieInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        return TopMovieInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topMovieInfoList.size
    }

    override fun onBindViewHolder(holder: TopMovieInfoViewHolder, position: Int) {
        val movieItem = topMovieInfoList[position]
        try {
            holder.tvTitle.text = movieItem.name
            Picasso.get().load(movieItem.imageUrl).into(holder.ivLogo)
        } catch (e: Exception) {
            Log.d("MainViewModel", e.toString())
        }

    }


    inner class TopMovieInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val ivLogo: ImageView = itemView.findViewById(R.id.iv_movie)
    }


}