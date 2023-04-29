package com.example.movieapp.data.mapper

import com.example.movieapp.data.database.MovieItemDbModel
import com.example.movieapp.data.network.models.MovieItemDto

class MovieMapper {

    fun mapDtoToDbModel(dto: MovieItemDto) = MovieItemDbModel(
        adult = dto.adult,
        backdropPath = dto.backdropPath,
        id = dto.id,
        originalLanguage = dto.originalLanguage,
        originalTitle = dto.originalTitle,
        overview = dto.overview,
        popularity = dto.popularity,
        posterPath = dto.posterPath,
        releaseDate = dto.releaseDate,
        title = dto.title,
        video = dto.video,
        voteAverage = dto.voteAverage,
        voteCount = dto.voteCount,
    )
}