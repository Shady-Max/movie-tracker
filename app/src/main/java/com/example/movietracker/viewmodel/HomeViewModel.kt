package com.example.movietracker.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietracker.RetrofitInstance
import com.example.movietracker.model.Movie
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getPopularMovies("5c221d58cbb4a23aa4a27e9aadd663c7")
                _movies.value = response.results
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching movies: ${e.message}")
            }
        }
    }
}