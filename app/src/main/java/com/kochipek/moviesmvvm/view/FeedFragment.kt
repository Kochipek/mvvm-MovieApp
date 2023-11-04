package com.kochipek.moviesmvvm.view

import MovieAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kochipek.moviesmvvm.R
import com.kochipek.moviesmvvm.databinding.FragmentFeedBinding
import com.kochipek.moviesmvvm.viewmodel.FeedViewModel


class FeedFragment : Fragment(R.layout.fragment_feed) {
    private lateinit var binding: FragmentFeedBinding
    private lateinit var viewModel: FeedViewModel
    private val movieAdapter = MovieAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeedBinding.bind(view)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.loadData()
        // !
        binding.movieList.layoutManager = LinearLayoutManager(context)
        binding.movieList.adapter = movieAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.movieList.visibility = View.GONE
            binding.movieError.visibility = View.GONE
            viewModel.loadData()
            binding.swipeRefreshLayout.isRefreshing = false
        }
        observeFeedData()
    }

    private fun observeFeedData() {
        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            movies?.let {
                binding.movieList.visibility = View.VISIBLE
                movieAdapter.updateWhenSwipe(movies)
            }
        })
        viewModel.loadingState.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                if (it) {
                    binding.movieLoadingBar.visibility = View.VISIBLE
                    binding.movieError.visibility = View.GONE
                    binding.movieList.visibility = View.GONE
                } else {
                    binding.movieLoadingBar.visibility = View.GONE
                }
            }
        })
        viewModel.movieError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    binding.movieError.visibility = View.VISIBLE
                } else {
                    binding.movieError.visibility = View.GONE
                }


            }
        })
    }

}