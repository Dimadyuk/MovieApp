package com.example.movieapp.presentation.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.repositories.MovieRepositoryImpl
import com.example.movieapp.domain.usecases.GetPopularMovieListUseCase
import com.example.movieapp.domain.usecases.LoadPopularMoviesUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val application: Application) : AndroidViewModel(application) {
    private val repository = MovieRepositoryImpl(application)
    private val loadPopularMoviesUseCase = LoadPopularMoviesUseCase(repository)
    private val getPopularMovieListUseCase = GetPopularMovieListUseCase(repository)

    var movieList = getPopularMovieListUseCase.invoke()

    init {
        getPopularMovieListUseCase
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    fun loadData() {
        if (isNetworkAvailable(application)) {
            viewModelScope.launch {
                loadPopularMoviesUseCase()
            }
        }

    }
}
