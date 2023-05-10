package com.example.movieapp.presentation.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.repositories.MovieRepositoryImpl
import com.example.movieapp.domain.MovieItem
import com.example.movieapp.domain.usecases.itemusecases.AddFavoriteMovieItemUseCase
import com.example.movieapp.domain.usecases.itemusecases.DeleteFavoriteMovieItemUseCase
import com.example.movieapp.domain.usecases.itemusecases.GetFavoriteMovieItemUseCase
import com.example.movieapp.domain.usecases.itemusecases.GetMovieItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailViewModel(private val application: Application) : AndroidViewModel(application) {
    private val repository = MovieRepositoryImpl(application)

    private val getMovieItemUseCase = GetMovieItemUseCase(repository)
    private val getFavoriteMovieItemUseCase = GetFavoriteMovieItemUseCase(repository)
    val deleteFavoriteMovieItemUseCase = DeleteFavoriteMovieItemUseCase(repository)
    val addFavoriteMovieItemUseCase = AddFavoriteMovieItemUseCase(repository)


    val movieItem = MutableLiveData<MovieItem>()

    suspend fun getMovieItem(movieId: Int) {
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            movieItem.postValue(
                getFavoriteMovieItemUseCase.invoke(movieId)
                    ?: getMovieItemUseCase.invoke(movieId)
            )
        }
    }
}