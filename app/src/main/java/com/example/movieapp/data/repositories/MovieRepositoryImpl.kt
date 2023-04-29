package com.example.movieapp.data.repositories

import android.app.Application
import com.example.movieapp.data.database.AppDatabase
import com.example.movieapp.data.mapper.MovieMapper
import com.example.movieapp.data.network.ApiFactory
import com.example.movieapp.data.network.models.ResponsePopularResultDto
import com.example.movieapp.domain.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepositoryImpl(application: Application) : MovieRepository {
    private val movieInfoDao = AppDatabase.getInstance(application).movieInfoDao()
    private val mapper = MovieMapper()
    override suspend fun loadPopularMovies(): ResponsePopularResultDto {
        val loadedList = ApiFactory.apiService.loadPopularMovies()
        val dbModelList = loadedList.results?.map {
            mapper.mapDtoToDbModel(it)
        }
        withContext(Dispatchers.IO) {
            dbModelList?.let {
                movieInfoDao.insertMovieList(it)
            }
        }



        return loadedList
    }
}