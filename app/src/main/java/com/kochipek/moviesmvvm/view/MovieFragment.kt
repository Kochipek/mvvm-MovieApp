package com.kochipek.moviesmvvm.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.kochipek.moviesmvvm.R
import com.kochipek.moviesmvvm.databinding.FragmentMovieBinding
import com.kochipek.moviesmvvm.viewmodel.MovieViewModel

class MovieFragment : Fragment(R.layout.fragment_movie) {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel

    private val args: MovieFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        viewModel.getMovieData()
        val movieUuid = args.movieUuid
        //println(movieUuid) -> 0
        observeMovieData()
    }

    private fun observeMovieData() {
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer { movie ->
            movie?.let {
                binding.movieTitle.text = movie.title
                binding.movieReleaseDate.text = movie.release_date
                binding.movieRating.text = movie.vote_average
                binding.movieDescription.text = movie.overview
            }
        })
    }
}