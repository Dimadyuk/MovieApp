package com.example.movieapp.domain.usecases.getlists

import com.example.movieapp.domain.MovieRepository

class GetPopularMovieListUseCase(private val repository: MovieRepository) {
    operator fun invoke() = repository.getPopularMoviesList()
}