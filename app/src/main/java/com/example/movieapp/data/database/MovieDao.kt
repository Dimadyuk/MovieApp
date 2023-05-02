package com.example.movieapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.domain.MovieItem

@Dao
interface MovieDao {

    @Query("SELECT * FROM popular_movie_info")
    fun getPopularMovieList(): LiveData<List<PopularMovieItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovieList(movieList: List<PopularMovieItemDbModel>)

    @Query("SELECT * FROM top_movie_info")
    fun getTopMovieList(): LiveData<List<TopMovieItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopMovieList(movieList: List<TopMovieItemDbModel>)

    @Query("SELECT * FROM popular_movie_info WHERE id = :movieItemId LIMIT 1")
    fun getMovieItem(movieItemId: Int): PopularMovieItemDbModel


}