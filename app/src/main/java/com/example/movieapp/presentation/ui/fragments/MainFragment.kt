package com.example.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMainBinding
import com.example.movieapp.domain.MovieItem
import com.example.movieapp.presentation.ui.MainViewModel
import com.example.movieapp.presentation.ui.fragments.adapters.MovieAdapter

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var popularAdapter: MovieAdapter
    private lateinit var topAdapter: MovieAdapter
    private lateinit var favoriteAdapter: MovieAdapter
    private val binding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        setupAdapters()
        setupObservers()

    }

    private fun setupAdapters() {
        popularAdapter = MovieAdapter()
        popularAdapter.onMovieItemClickListener = object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(movieItem: MovieItem) {
                val fragment = MovieDetailFragment.newInstance(movieItem.id)
                launchFragment(fragment)
            }
        }
        binding.rvPopularMovies.adapter = popularAdapter
        //TODO need to add swipeListener
//        binding.swipePopularMovies.setOnRefreshListener {
//            viewModel.loadPopularMovies()
//            binding.swipePopularMovies.isRefreshing = false
//        }

        topAdapter = MovieAdapter()
        topAdapter.onMovieItemClickListener = object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(movieItem: MovieItem) {
                val fragment = MovieDetailFragment.newInstance(movieItem.id)
                launchFragment(fragment)
            }
        }
        binding.rvTopMovies.adapter = topAdapter
        //TODO need to add swipeListener
//        binding.swipeTopMovies.setOnRefreshListener {
//            viewModel.loadTopMovies()
//            binding.swipeTopMovies.isRefreshing = false
//        }

        favoriteAdapter = MovieAdapter()
        favoriteAdapter.onMovieItemClickListener = object : MovieAdapter.OnItemClickListener {
            override fun onItemClick(movieItem: MovieItem) {
                val fragment = MovieDetailFragment.newInstance(movieItem.id)
                launchFragment(fragment)
            }
        }
        binding.rvMyFavouritesMovies.adapter = favoriteAdapter
    }

    private fun launchFragment(fragment: Fragment) {
        parentFragmentManager.popBackStack()
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupObservers() {
        viewModel.popularMovieList.observe(requireActivity()) {
            popularAdapter.submitList(it)
        }
        viewModel.topMovieList.observe(requireActivity()) {
            topAdapter.submitList(it)
        }
        viewModel.favoriteMovieList.observe(requireActivity()) {
            favoriteAdapter.submitList(it)
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}