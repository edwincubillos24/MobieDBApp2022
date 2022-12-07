package com.cubidevs.mobiedbapp2022.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cubidevs.mobiedbapp2022.local.LocalMovie
import com.cubidevs.mobiedbapp2022.local.repository.LocalMoviesRepository
import com.cubidevs.mobiedbapp2022.server.model.Movie
import kotlinx.coroutines.launch
import java.sql.Types.NULL

class DetailViewModel : ViewModel() {

    val localMoviesRepository = LocalMoviesRepository()

    private val _movieExist : MutableLiveData<Boolean> = MutableLiveData()
    val movieExist: LiveData<Boolean> = _movieExist

    fun addMovieToFavorites(movie: Movie) {
        val localMovie = LocalMovie(
            id = movie.id,
            name = movie.title,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            voteAverage = movie.voteAverage,
            overview = movie.overview)

        viewModelScope.launch {
            localMoviesRepository.saveMovie(localMovie)
        }
    }

    fun searchMovie(id: Int?) {
        var movieExistAux = false
        viewModelScope.launch{
            val localMovie = localMoviesRepository.searchMovie(id)
            if (localMovie != null)
                movieExistAux = true
            _movieExist.postValue(movieExistAux)
        }
    }
}