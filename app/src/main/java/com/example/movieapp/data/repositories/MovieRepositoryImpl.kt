package com.example.movieapp.data.repositories

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.movieapp.data.database.AppDatabase
import com.example.movieapp.data.mapper.MovieMapper
import com.example.movieapp.data.network.ApiFactory
import com.example.movieapp.domain.MovieItem
import com.example.movieapp.domain.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(application: Application) : MovieRepository {
    private val movieInfoDao = AppDatabase.getInstance(application).movieInfoDao()
    private val mapper = MovieMapper()
    override suspend fun loadPopularMovies() {
        val loadedList = withContext(Dispatchers.Default) {
            ApiFactory.apiService.loadPopularMovies()
        }
        val dbModelList = loadedList.results?.map {
            mapper.mapPopularDtoToDbModel(it)
        }
        withContext(Dispatchers.IO) {
            dbModelList?.let {
                movieInfoDao.insertPopularMovieList(it)
                Log.d("MainViewModel", it.toString())
            }
        }
    }

    override fun getPopularMoviesList(): LiveData<List<MovieItem>> {
        return movieInfoDao.getPopularMovieList().map { list ->
            list.map { mapper.mapDbModelToMovieItem(it) }
        }
    }

    override suspend fun loadTopRatedMovies() {
        val loadedList = withContext(Dispatchers.Default) {
            ApiFactory.apiService.loadTopRatedMovies()
        }
        val dbModelList = loadedList.results?.map {
            mapper.mapTopDtoToDbModel(it)
        }
        withContext(Dispatchers.IO) {
            dbModelList?.let {
                movieInfoDao.insertTopMovieList(it)
                Log.d("MainViewModel", it.toString())
            }
        }
    }

    override fun getTopMoviesList(): LiveData<List<MovieItem>> {
        return movieInfoDao.getTopMovieList().map { list ->
            list.map { mapper.mapDbModelToMovieItem(it) }
        }
    }

    override suspend fun getMovieItem(movieItemId: Int): MovieItem {
        val dbModel = movieInfoDao.getMovieItem(movieItemId)
        return mapper.mapDbModelToMovieItem(dbModel)
    }

    override fun getFavoriteMoviesList(): LiveData<List<MovieItem>> {
        return movieInfoDao.getFavoriteMovieList().map { list ->
            list.map { mapper.mapFavoriteDbModelToMovieItem(it) }
        }
    }

    override suspend fun addFavoriteMovieItem(movieItemId: Int) {
        val dbModel = movieInfoDao.getMovieItem(movieItemId)
        movieInfoDao.addFavoriteMovieItem(mapper.mapDbModelToFavoriteDbModel(dbModel))
    }

    override suspend fun getFavoriteMovieItem(movieItemId: Int): MovieItem? {
        return movieInfoDao.getFavoriteMovieItem(movieItemId)?.let {
            mapper.mapFavoriteDbModelToMovieItem(it)
        }

    }

    override suspend fun deleteFavoriteMovieItem(movieItemId: Int) {
        movieInfoDao.deleteFavoriteMovieItem(movieItemId)
    }
}