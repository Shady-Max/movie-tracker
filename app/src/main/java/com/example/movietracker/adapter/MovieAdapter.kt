package com.example.movietracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietracker.R
import com.example.movietracker.model.Movie
import com.example.movietracker.viewmodel.SharedViewModel

class MovieAdapter(private var movieList: List<Movie>, private val sharedViewModel: SharedViewModel) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val yearTextView: TextView = itemView.findViewById(R.id.textViewYear)
        val posterImageView: ImageView = itemView.findViewById(R.id.imageViewPoster)
        val watchlistButton: Button = itemView.findViewById(R.id.watchlistButton)

        fun bind(movie: Movie, sharedViewModel: SharedViewModel) {
            titleTextView.text = movie.title
            yearTextView.text = "Release Year: " + movie.releaseYear.toString()

            Glide.with(itemView.context).load(movie.posterUrl).placeholder(R.drawable.ic_launcher_background).into(posterImageView)

            updateButtonText(movie, sharedViewModel)

            watchlistButton.setOnClickListener {
                if (sharedViewModel.isInWatchlist(movie)) {
                    sharedViewModel.removeFromWatchlist(movie)
                } else {
                    sharedViewModel.addToWatchlist(movie)
                }
                updateButtonText(movie, sharedViewModel)
            }
        }

        private fun updateButtonText(movie: Movie, sharedViewModel: SharedViewModel) {
            watchlistButton.text = if (sharedViewModel.isInWatchlist(movie)) "Remove" else "Add to Watchlist"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position], sharedViewModel)
    }

    fun updateMovies(newMovies: List<Movie>) {
        movieList = newMovies
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = movieList.size
}