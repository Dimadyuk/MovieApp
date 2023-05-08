package com.example.movieapp.domain

import androidx.lifecycle.LiveData

interface MovieRepository {
    suspend fun loadPopularMovies()

    fun getPopularMoviesList(): LiveData<List<MovieItem>>
    suspend fun loadTopRatedMovies()

    fun getTopMoviesList(): LiveData<List<MovieItem>>
    suspend fun getMovieItem(movieItemId: Int): MovieItem
    fun getFavoriteMoviesList(): LiveData<List<MovieItem>>
    suspend fun addFavoriteMovieItem(movieItem: MovieItem)
}