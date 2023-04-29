package com.example.movieapp.domain

import com.example.movieapp.data.network.models.ResponsePopularResultDto

interface MovieRepository {
    suspend fun loadPopularMovies(): ResponsePopularResultDto
}