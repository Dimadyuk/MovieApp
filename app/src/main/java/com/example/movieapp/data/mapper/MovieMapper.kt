package com.example.movieapp.data.mapper

import com.example.movieapp.data.database.FavoriteMovieItemDbModel
import com.example.movieapp.data.database.MovieItemDbModel
import com.example.movieapp.data.network.ApiFactory
import com.example.movieapp.data.network.models.MovieItemDto
import com.example.movieapp.domain.MovieItem

class MovieMapper {

    fun mapPopularDtoToDbModel(dto: MovieItemDto) = MovieItemDbModel(
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
        isPopular = true,
        isTop = false
    )

    fun mapTopDtoToDbModel(dto: MovieItemDto) = MovieItemDbModel(
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
        isPopular = false,
        isTop = true
    )

    fun mapDbModelToMovieItem(dbModel: MovieItemDbModel): MovieItem {
        return MovieItem(
            id = dbModel.id,
            name = dbModel.originalTitle,
            imageUrl = dbModel.backdropPath,
            backdropPath = dbModel.backdropPath,
            posterPath = dbModel.posterPath,
            overview = dbModel.overview
        )
    }

    fun mapFavoriteDbModelToMovieItem(dbModel: FavoriteMovieItemDbModel): MovieItem {
        return MovieItem(
            id = dbModel.id,
            name = dbModel.originalTitle,
            imageUrl = dbModel.backdropPath,
            backdropPath = dbModel.backdropPath,
            posterPath = dbModel.posterPath,
            overview = dbModel.overview
        )
    }

    fun mapMovieItemToFavoriteDbModel(movieItem: MovieItem): FavoriteMovieItemDbModel {
        return FavoriteMovieItemDbModel(
            id = movieItem.id,
            backdropPath = movieItem.backdropPath,
            originalLanguage = movieItem.name,
            originalTitle = movieItem.name,
            overview = movieItem.overview,
            popularity = 1.0,
            posterPath = movieItem.overview,
            releaseDate = movieItem.overview,
            title = movieItem.overview,
            video = true,
            voteAverage = 1.0,
            voteCount = 1,
            adult = true,
            isTop = true,
            isPopular = true
        )
    }
}