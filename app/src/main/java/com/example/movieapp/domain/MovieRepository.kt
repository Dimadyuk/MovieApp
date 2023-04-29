package com.example.movieapp.domain

import androidx.lifecycle.LiveData

interface MovieRepository {
    suspend fun loadPopularMovies()

    fun getPopularMoviesList(): LiveData<List<MovieItem>>
}