package com.example.movieapp.presentation.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.repositories.MovieRepositoryImpl
import com.example.movieapp.domain.MovieItem
import com.example.movieapp.domain.usecases.AddFavoriteMovieItemUseCase
import com.example.movieapp.domain.usecases.DeleteFavoriteMovieItemUseCase
import com.example.movieapp.domain.usecases.GetFavoriteMovieItemUseCase
import com.example.movieapp.domain.usecases.GetFavoriteMovieListUseCase
import com.example.movieapp.domain.usecases.GetMovieItemUseCase
import com.example.movieapp.domain.usecases.GetPopularMovieListUseCase
import com.example.movieapp.domain.usecases.GetTopMovieListUseCase
import com.example.movieapp.domain.usecases.LoadPopularMoviesUseCase
import com.example.movieapp.domain.usecases.LoadTopRatedMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val application: Application) : AndroidViewModel(application) {
    private val repository = MovieRepositoryImpl(application)

    private val loadPopularMoviesUseCase = LoadPopularMoviesUseCase(repository)
    private val loadTopRatedMoviesUseCase = LoadTopRatedMoviesUseCase(repository)

    private val getPopularMovieListUseCase = GetPopularMovieListUseCase(repository)
    private val getTopMovieListUseCase = GetTopMovieListUseCase(repository)
    private val getFavoriteMovieListUseCase = GetFavoriteMovieListUseCase(repository)

    private val getMovieItemUseCase = GetMovieItemUseCase(repository)
    private val getFavoriteMovieItemUseCase = GetFavoriteMovieItemUseCase(repository)
    val deleteFavoriteMovieItemUseCase = DeleteFavoriteMovieItemUseCase(repository)
    val addFavoriteMovieItemUseCase = AddFavoriteMovieItemUseCase(repository)

    val popularMovieList = getPopularMovieListUseCase.invoke()
    val topMovieList = getTopMovieListUseCase.invoke()
    val favoriteMovieList = getFavoriteMovieListUseCase.invoke()


    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    suspend fun getMovieItem(movieId: Int): MovieItem {
        return withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            getFavoriteMovieItemUseCase.invoke(movieId) ?: getMovieItemUseCase.invoke(movieId)
        }

    }

    fun loadData() {
        if (isNetworkAvailable(application)) {
            viewModelScope.launch {
                loadPopularMoviesUseCase()
            }
            viewModelScope.launch {
                loadTopRatedMoviesUseCase()
            }

        }

    }
}
