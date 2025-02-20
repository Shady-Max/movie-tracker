package com.example.movietracker.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("release_date") val fullReleaseDate: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val votes: Float,
    var isWatchlisted: Boolean = false
) {
    val posterUrl: String get() = "https://image.tmdb.org/t/p/w500$posterPath"
    val releaseYear: String get() = fullReleaseDate.take(4)
}