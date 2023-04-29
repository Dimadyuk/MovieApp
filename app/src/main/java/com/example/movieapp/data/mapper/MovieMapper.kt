package com.example.movieapp.data.mapper

import com.example.movieapp.data.database.MovieItemDbModel
import com.example.movieapp.data.network.ApiFactory
import com.example.movieapp.data.network.models.MovieItemDto
import com.example.movieapp.domain.MovieItem

class MovieMapper {

    fun mapDtoToDbModel(dto: MovieItemDto) = MovieItemDbModel(
        adult = dto.adult,
        backdropPath = ApiFactory.BASE_IMAGE_URL + dto.backdropPath,
        id = dto.id ?: -1,
        originalLanguage = dto.originalLanguage,
        originalTitle = dto.originalTitle ?: "",
        overview = dto.overview,
        popularity = dto.popularity,
        posterPath = ApiFactory.BASE_IMAGE_URL + dto.posterPath,
        releaseDate = dto.releaseDate,
        title = dto.title,
        video = dto.video,
        voteAverage = dto.voteAverage,
        voteCount = dto.voteCount,
    )

    fun mapDbModelToMovieItem(dbModel: MovieItemDbModel): MovieItem {
        return MovieItem(
            id = dbModel.id,
            name = dbModel.originalTitle,
            imageUrl = dbModel.backdropPath
        )
    }
}