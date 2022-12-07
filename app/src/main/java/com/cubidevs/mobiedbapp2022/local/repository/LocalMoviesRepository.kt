package com.cubidevs.mobiedbapp2022.local.repository

import com.cubidevs.mobiedbapp2022.MobieDBApp2022
import com.cubidevs.mobiedbapp2022.local.LocalMovie
import com.cubidevs.mobiedbapp2022.local.MovieDao

class LocalMoviesRepository {

    suspend fun saveMovie(localMovie: LocalMovie) {
        val movieDao : MovieDao = MobieDBApp2022.database.MovieDao()
        movieDao.createMovie(localMovie)
    }

    suspend fun getMovies() = MobieDBApp2022.database.MovieDao().getMovies()

    suspend fun deleteMovie(localMovie: LocalMovie) {
        val movieDao : MovieDao = MobieDBApp2022.database.MovieDao()
        movieDao.deleteMovie(localMovie)
    }

    suspend fun searchMovie(id: Int?) = MobieDBApp2022.database.MovieDao().searchMovie(id)
    }