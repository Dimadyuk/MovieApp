package com.example.movieapp.data.repositories

import com.example.movieapp.data.network.ApiFactory
import com.example.movieapp.data.network.models.ResponsePopularResultDto
import com.example.movieapp.domain.MovieRepository

class MovieRepositoryImpl : MovieRepository {
    override suspend fun getPopularMovies(): ResponsePopularResultDto {
        return ApiFactory.apiService.getPopularMovies()
    }
}