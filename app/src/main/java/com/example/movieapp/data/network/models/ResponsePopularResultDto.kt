package com.example.movieapp.data.network.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ResponsePopularResultDto(
    @SerializedName("page")
    @Expose
    val page: Int? = null,

    @SerializedName("results")
    @Expose
    val results: List<PopularResultDao>? = null,

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int? = null,

    @SerializedName("total_results")
    @Expose
    val totalResults: Int? = null,
)
