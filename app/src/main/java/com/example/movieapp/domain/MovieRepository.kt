package com.example.movieapp.domain

import androidx.lifecycle.LiveData
import com.example.movieapp.data.network.models.ResponsePopularResultDto

interface MovieRepository {
    suspend fun loadPopularMovies()

    fun getPopularMoviesList(): LiveData<List<MovieItem>>
}