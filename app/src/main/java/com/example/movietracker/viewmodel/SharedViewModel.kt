package com.example.movietracker.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movietracker.model.Movie

class SharedViewModel : ViewModel() {
    private val _watchlist = MutableLiveData<MutableList<Movie>>(mutableListOf())
    val watchlist: LiveData<MutableList<Movie>> = _watchlist

    fun addToWatchlist(movie: Movie) {
        if (!_watchlist.value!!.contains(movie)) {
            _watchlist.value!!.add(movie)
            _watchlist.value = _watchlist.value  // Notify observers
            Log.d("data", _watchlist.value.toString())
        }
    }

    fun removeFromWatchlist(movie: Movie) {
        _watchlist.value = _watchlist.value!!.filter { it.id != movie.id }.toMutableList()
    }

    fun isInWatchlist(movie: Movie): Boolean {
        return _watchlist.value!!.any { it.id == movie.id }
    }
}