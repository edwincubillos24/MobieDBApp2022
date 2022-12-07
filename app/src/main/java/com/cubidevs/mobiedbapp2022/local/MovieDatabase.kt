package com.cubidevs.mobiedbapp2022.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun MovieDao(): MovieDao

}