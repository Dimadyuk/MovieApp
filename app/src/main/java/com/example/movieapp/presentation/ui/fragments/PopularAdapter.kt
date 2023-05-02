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

class PopularAdapter() :
    RecyclerView.Adapter<PopularAdapter.MovieInfoViewHolder>() {

    var movieInfoList: List<MovieItem> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onMovieItemClickListener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        return MovieInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieInfoList.size
    }

    override fun onBindViewHolder(holder: MovieInfoViewHolder, position: Int) {
        val movieItem = movieInfoList[position]
        try {
            holder.tvTitle.text = movieItem.name
            Picasso.get().load(movieItem.imageUrl).into(holder.ivLogo)
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

    inner class MovieInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val ivLogo: ImageView = itemView.findViewById(R.id.iv_movie)
    }


}