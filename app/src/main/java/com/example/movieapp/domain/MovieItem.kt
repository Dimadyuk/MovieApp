package com.example.movieapp.domain

data class MovieItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val backdropPath: String,
    val posterPath: String?,
    val overview: String?,
    val isFavorite: Boolean
){
    companion object{
        const val UNDEFINED_ID = 0
    }
}