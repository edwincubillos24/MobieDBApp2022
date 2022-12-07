package com.cubidevs.mobiedbapp2022.server.moviesrepository

import com.cubidevs.mobiedbapp2022.server.MovieDB

class MoviesRepository {

    private val apiKey = "ff29f617b45b36aab5aa78a6fa04677f"

    suspend fun getMovies() = MovieDB.retrofit.getMovies(apiKey)
}