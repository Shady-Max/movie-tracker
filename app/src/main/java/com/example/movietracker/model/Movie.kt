package com.example.movietracker.model

data class Movie(
    val id: Int,
    val title: String,
    val releaseYear: Int,
    val posterUrl: String,
    var isWatchlisted: Boolean = false
)