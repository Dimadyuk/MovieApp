package com.example.movieapp.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_info")
data class MovieItemDbModel(
    @PrimaryKey
    val id: Int,
    val adult: Boolean?,
    val backdropPath: String,
    val originalLanguage: String?,
    val originalTitle: String,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val isPopular: Boolean,
    val isTop: Boolean
)