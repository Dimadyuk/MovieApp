package com.example.movieapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_info WHERE isPopular = 1")
    fun getPopularMovieList(): LiveData<List<MovieItemDbModel>>

    @Query("SELECT * FROM movie_info WHERE isTop = 1")
    fun getTopMovieList(): LiveData<List<MovieItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovieList(movieList: List<MovieItemDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopMovieList(movieList: List<MovieItemDbModel>)

    @Query("SELECT * FROM movie_info WHERE id = :movieItemId LIMIT 1")
    fun getMovieItem(movieItemId: Int): MovieItemDbModel


}