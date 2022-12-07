package com.cubidevs.mobiedbapp2022.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cubidevs.mobiedbapp2022.local.LocalMovie
import com.cubidevs.mobiedbapp2022.local.repository.LocalMoviesRepository
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {

    var localMoviesRepository = LocalMoviesRepository()

    private val _moviesLoaded: MutableLiveData<ArrayList<LocalMovie>> = MutableLiveData()
    val moviesLoaded: LiveData<ArrayList<LocalMovie>> = _moviesLoaded

    fun loadMovies() {
        viewModelScope.launch {
            val listFavoritesMovies = localMoviesRepository.getMovies()
            _moviesLoaded.postValue(listFavoritesMovies as ArrayList<LocalMovie>)
        }
    }

    fun deleteMovie(localMovie: LocalMovie) {
        viewModelScope.launch {
            localMoviesRepository.deleteMovie(localMovie)
        }
    }
}