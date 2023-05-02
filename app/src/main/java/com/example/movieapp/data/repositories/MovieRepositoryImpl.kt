package com.example.movieapp.data.repositories

import android.app.Application
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
            }
        }
    }

    override fun getPopularMoviesList(): LiveData<List<MovieItem>> {
        return movieInfoDao.getPopularMovieList().map { list ->
            list.map { mapper.mapPopularDbModelToMovieItem(it) }
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
            }
        }
    }

    override fun getTopMoviesList(): LiveData<List<MovieItem>> {
        return movieInfoDao.getTopMovieList().map { list ->
            list.map { mapper.mapTopDbModelToMovieItem(it) }
        }
    }
}