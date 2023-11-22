package com.kochipek.moviesmvvm.view

import MovieAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
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

        binding.movieList.layoutManager = LinearLayoutManager(context)
        binding.movieList.adapter = movieAdapter
        viewModel.loadData(this.requireContext())
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadData(this.requireContext())
            binding.movieList.visibility = View.GONE
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeFeedData()
    }

    private fun observeFeedData() {
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            movies?.let {
                binding.movieList.visibility = View.VISIBLE
                movieAdapter.updateWhenSwipe(movies)
            }
        }

        viewModel.loadingState.observe(viewLifecycleOwner) { isLoading ->
            isLoading?.let {
                binding.movieLoadingBar.visibility = if (it) View.VISIBLE else View.GONE
                binding.movieList.visibility = if (it) View.GONE else View.VISIBLE
            }
        }
    }
}
