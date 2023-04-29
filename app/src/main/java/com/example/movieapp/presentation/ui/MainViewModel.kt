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
    var movieList = MutableLiveData<List<MovieItem>>()
    fun loadData() {
        viewModelScope.launch {
            val jsonContainer = GetPopularMoviesUseCase(repository).invoke()
            //TODO need rework to useCases
            val listOfMovies = mutableListOf<MovieItem>()
            val keySet = jsonContainer.results
            if (keySet != null) {
                for (currentKey in keySet) {
                    val name = currentKey.originalTitle ?: ""
                    val id = currentKey.id ?: 0
                    val image = ApiFactory.BASE_IMAGE_URL + (currentKey.backdropPath ?: "")
                    val newItem = MovieItem(id = id, name = name, imageUrl = image)
                    listOfMovies.add(newItem)
                }
                movieList.value = listOfMovies
            }






            Log.d("MainViewModel", jsonContainer.toString())
        }
    }
}
