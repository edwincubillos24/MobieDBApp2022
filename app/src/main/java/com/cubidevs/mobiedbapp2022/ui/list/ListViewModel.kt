package com.cubidevs.mobiedbapp2022.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cubidevs.mobiedbapp2022.server.model.Movie
import com.cubidevs.mobiedbapp2022.server.model.MoviesList
import com.cubidevs.mobiedbapp2022.server.moviesrepository.MoviesRepository
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val moviesRepository = MoviesRepository()

    private val _moviesLoaded : MutableLiveData<ArrayList<Movie>> = MutableLiveData()
    val moviesLoaded: LiveData<ArrayList<Movie>> = _moviesLoaded

    fun getMovies() {
        viewModelScope.launch {
            val moviesList : MoviesList = moviesRepository.getMovies()
            _moviesLoaded.postValue(moviesList.movies as ArrayList<Movie>)
        }
    }
}