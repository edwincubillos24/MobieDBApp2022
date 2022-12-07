package com.cubidevs.mobiedbapp2022.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_movie")
data class LocalMovie(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "name")val name: String?,
    @ColumnInfo(name = "posterPath")val posterPath: String?,
    @ColumnInfo(name = "releaseDate")val releaseDate: String?,
    @ColumnInfo(name = "voteAverage")val voteAverage: Double?,
    @ColumnInfo(name = "overview")val overview: String?
)