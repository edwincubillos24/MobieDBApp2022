package com.cubidevs.mobiedbapp2022.server

import com.cubidevs.mobiedbapp2022.server.model.MoviesList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apiKey: String) : MoviesList

}