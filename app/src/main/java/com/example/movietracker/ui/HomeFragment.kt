package com.example.movietracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movietracker.R
import com.example.movietracker.adapter.MovieAdapter
import com.example.movietracker.model.Movie
import com.example.movietracker.viewmodel.HomeViewModel
import com.example.movietracker.viewmodel.SharedViewModel

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var movieRecyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    private val sharedViewModel: SharedViewModel by activityViewModels()

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
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        movieAdapter = MovieAdapter(emptyList(), sharedViewModel)
        movieRecyclerView.adapter = movieAdapter

        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            movieAdapter.updateMovies(movies)
        }
    }
}