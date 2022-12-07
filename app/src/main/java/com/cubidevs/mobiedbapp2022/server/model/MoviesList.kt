package com.cubidevs.mobiedbapp2022.server.model


import com.google.gson.annotations.SerializedName

data class MoviesList(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val movies: List<Movie?>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
)