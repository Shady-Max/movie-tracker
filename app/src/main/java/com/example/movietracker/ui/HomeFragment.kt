package com.example.movietracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietracker.R
import com.example.movietracker.adapter.MovieAdapter
import com.example.movietracker.model.Movie
import com.example.movietracker.viewmodel.SharedViewModel

class HomeFragment : Fragment() {
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val movies = listOf(
        Movie(0,"Inception", 2010, "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_FMjpg_UX700_.jpg"),
        Movie(1,"The Dark Knight", 2008, "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg"),
        Movie(2, "Interstellar", 2014, "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieRecyclerView = view.findViewById(R.id.recyclerViewMovies)
        movieRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Sample movie list


        movieAdapter = MovieAdapter(movies, sharedViewModel)
        movieRecyclerView.adapter = movieAdapter
    }
}