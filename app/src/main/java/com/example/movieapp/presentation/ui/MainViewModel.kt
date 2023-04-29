package com.example.movieapp.presentation.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.repositories.MovieRepositoryImpl
import com.example.movieapp.domain.usecases.GetPopularMovieListUseCase
import com.example.movieapp.domain.usecases.LoadPopularMoviesUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MovieRepositoryImpl(application)
    private val loadPopularMoviesUseCase = LoadPopularMoviesUseCase(repository)
    private val getPopularMovieListUseCase = GetPopularMovieListUseCase(repository)

    var movieList = getPopularMovieListUseCase.invoke()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            loadPopularMoviesUseCase()
        }
    }
}
