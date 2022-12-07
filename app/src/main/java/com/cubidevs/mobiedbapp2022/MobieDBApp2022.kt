package com.cubidevs.mobiedbapp2022

import android.app.Application
import androidx.room.Room
import com.cubidevs.mobiedbapp2022.local.MovieDatabase

class MobieDBApp2022 : Application() {

    companion object{
        lateinit var database: MovieDatabase
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            this,
            MovieDatabase::class.java,
            "movie_db"
        ).build()
    }

}