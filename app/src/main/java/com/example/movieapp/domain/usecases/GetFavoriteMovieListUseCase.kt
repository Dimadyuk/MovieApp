package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.MovieRepository

class GetFavoriteMovieListUseCase(private val repository: MovieRepository) {
    operator fun invoke() = repository.getFavoriteMoviesList()
}