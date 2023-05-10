package com.example.movieapp.presentation.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.repositories.MovieRepositoryImpl
import com.example.movieapp.domain.usecases.getlists.GetFavoriteMovieListUseCase
import com.example.movieapp.domain.usecases.getlists.GetPopularMovieListUseCase
import com.example.movieapp.domain.usecases.getlists.GetTopMovieListUseCase
import com.example.movieapp.domain.usecases.loadlists.LoadPopularMoviesUseCase
import com.example.movieapp.domain.usecases.loadlists.LoadTopRatedMoviesUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val application: Application) : AndroidViewModel(application) {
    private val repository = MovieRepositoryImpl(application)

    private val loadPopularMoviesUseCase = LoadPopularMoviesUseCase(repository)
    private val loadTopRatedMoviesUseCase = LoadTopRatedMoviesUseCase(repository)

    private val getPopularMovieListUseCase = GetPopularMovieListUseCase(repository)
    private val getTopMovieListUseCase = GetTopMovieListUseCase(repository)
    private val getFavoriteMovieListUseCase = GetFavoriteMovieListUseCase(repository)

    val popularMovieList = getPopularMovieListUseCase.invoke()
    val topMovieList = getTopMovieListUseCase.invoke()
    val favoriteMovieList = getFavoriteMovieListUseCase.invoke()

    init {
        loadData()
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }



    private fun loadData() {
        loadPopularMovies()
        loadTopMovies()
    }

    private fun loadPopularMovies() {
        if (isNetworkAvailable(application)) {
            viewModelScope.launch {
                loadPopularMoviesUseCase()
            }
        }
    }

    private fun loadTopMovies() {
        if (isNetworkAvailable(application)) {
            viewModelScope.launch {
                loadTopRatedMoviesUseCase()
            }
        }
    }

}
