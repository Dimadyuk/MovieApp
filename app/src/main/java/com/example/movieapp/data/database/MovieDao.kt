package com.example.movieapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.database.models.FavoriteMovieItemDbModel
import com.example.movieapp.data.database.models.MovieItemDbModel

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_info WHERE isPopular = 1")
    fun getPopularMovieList(): LiveData<List<MovieItemDbModel>>

    @Query("SELECT * FROM movie_info WHERE isTop = 1")
    fun getTopMovieList(): LiveData<List<MovieItemDbModel>>

    @Query("SELECT * FROM favorite_movie_info")
    fun getFavoriteMovieList(): LiveData<List<FavoriteMovieItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovieList(movieList: List<MovieItemDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopMovieList(movieList: List<MovieItemDbModel>)

    @Query("SELECT * FROM movie_info WHERE id = :movieItemId LIMIT 1")
    fun getMovieItem(movieItemId: Int): MovieItemDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteMovieItem(movieItem: FavoriteMovieItemDbModel)

    @Query("SELECT * FROM favorite_movie_info WHERE id = :movieItemId LIMIT 1")
    fun getFavoriteMovieItem(movieItemId: Int): FavoriteMovieItemDbModel?
    @Query("DELETE FROM favorite_movie_info WHERE id = :movieItemId")
    fun deleteFavoriteMovieItem(movieItemId: Int)


}