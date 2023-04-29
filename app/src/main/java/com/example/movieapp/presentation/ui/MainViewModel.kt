package com.example.movieapp.presentation.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.network.ApiFactory
import com.example.movieapp.data.repositories.MovieRepositoryImpl
import com.example.movieapp.domain.MovieItem
import com.example.movieapp.domain.usecases.GetPopularMoviesUseCase
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {
    private val repository = MovieRepositoryImpl()
    private val getPopularMoviesUseCase = GetPopularMoviesUseCase(repository)
    var movieList = MutableLiveData<List<MovieItem>>()

init {
    loadData()
}
    fun loadData() {
        viewModelScope.launch {
            val responseContainer = getPopularMoviesUseCase()
            //TODO need rework to useCases
            val listOfMovies = mutableListOf<MovieItem>()
            val listOfResult = responseContainer.results
            if (listOfResult != null) {
                for (result in listOfResult) {
                    val name = result.originalTitle ?: ""
                    val id = result.id ?: 0
                    val image = ApiFactory.BASE_IMAGE_URL + (result.backdropPath ?: "")
                    val newItem = MovieItem(id = id, name = name, imageUrl = image)
                    listOfMovies.add(newItem)
                }
                movieList.value = listOfMovies
            }
            Log.d(
                "MainViewModel",
                this@MainViewModel.toString() + " - " + responseContainer.toString()
            )
        }
    }
}
