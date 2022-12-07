package com.cubidevs.mobiedbapp2022.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert
    suspend fun createMovie(movie: LocalMovie)

    @Query("SELECT * FROM table_movie")
    suspend fun getMovies() : MutableList<LocalMovie>

    @Delete
    suspend fun deleteMovie(localMovie: LocalMovie)

    @Query("SELECT * FROM table_movie WHERE id LIKE :id")
    suspend fun searchMovie(id: Int?): LocalMovie

}