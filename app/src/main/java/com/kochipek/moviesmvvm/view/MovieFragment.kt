package com.kochipek.moviesmvvm.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.kochipek.moviesmvvm.R
import com.kochipek.moviesmvvm.databinding.FragmentMovieBinding
import com.kochipek.moviesmvvm.utils.downloadFromUrl
import com.kochipek.moviesmvvm.utils.placeholderProgressBar
import com.kochipek.moviesmvvm.viewmodel.MovieViewModel

class MovieFragment : Fragment(R.layout.fragment_movie) {
    private lateinit var binding: FragmentMovieBinding
    private lateinit var viewModel: MovieViewModel

    private val args: MovieFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        viewModel.getDataFromRoomDb(args.movieUuid, this.requireContext())
        observeMovieData()
    }

    private fun observeMovieData() {
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer { movie ->
            movie?.let {
                binding.movieTitle.text = movie.title
                binding.movieReleaseDate.text = movie.release_date
                binding.movieRating.text = String.format("%.1f", movie.vote_average?.toDouble())
                binding.movieDescription.text = movie.overview
                binding.imageView.downloadFromUrl("https://image.tmdb.org/t/p/w500${movie.poster_path}", placeholderProgressBar(binding.root.context))
            }
        })
    }
}