package com.example.movieapp.presentation.ui.fragments.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.domain.MovieItem
import com.squareup.picasso.Picasso

class FavoriteAdapter() :
    ListAdapter<MovieItem, FavoriteAdapter.FavoriteMovieInfoViewHolder>(MovieItemDiffCallBack()) {

    var onMovieItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        return FavoriteMovieInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteMovieInfoViewHolder, position: Int) {
        val movieItem = getItem(position)
        try {
            holder.tvTitle.text = movieItem.name
            Picasso.get()
                .load(movieItem.imageUrl)
                .resize(150,150)
                .centerCrop()
                .into(holder.ivLogo)
        } catch (e: Exception) {
            Log.d("MainViewModel", e.toString())
        }
        holder.itemView.setOnClickListener {
            onMovieItemClickListener?.onItemClick(movieItem)
        }

    }

    interface OnItemClickListener {
        fun onItemClick(movieItem: MovieItem)
    }

    inner class FavoriteMovieInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val ivLogo: ImageView = itemView.findViewById(R.id.iv_movie)
    }


}