package com.example.movieapp.data.network

import com.example.movieapp.data.network.models.ResponsePopularResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/3/movie/popular")
    suspend fun loadPopularMovies(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LANGUAGE) language: String = LANGUAGE,
        @Query(QUERY_PARAM_PAGE) page: String = "1"
    ): ResponsePopularResultDto

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LANGUAGE = "language"
        private const val QUERY_PARAM_PAGE = "page"

        private const val API_KEY = "5351cd036255c6db897e15fe74841989"
        private const val LANGUAGE = "en-US"

    }
}