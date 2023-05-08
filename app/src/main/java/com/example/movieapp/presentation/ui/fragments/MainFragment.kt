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

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var popularAdapter: PopularAdapter
    private lateinit var topAdapter: TopAdapter
    private lateinit var favoriteAdapter: FavoriteAdapter
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
        viewModel.loadData()
    }

    private fun setupAdapters() {
        popularAdapter = PopularAdapter()
        popularAdapter.onMovieItemClickListener = object : PopularAdapter.OnItemClickListener {
            override fun onItemClick(movieItem: MovieItem) {
                val fragment = MovieDetailFragment.newInstance(movieItem.id)
                launchFragment(fragment)
            }
        }
        binding.rvPopularMovies.adapter = popularAdapter

        topAdapter = TopAdapter()
        topAdapter.onMovieItemClickListener = object : TopAdapter.OnItemClickListener {
            override fun onItemClick(movieItem: MovieItem) {
                val fragment = MovieDetailFragment.newInstance(movieItem.id)
                launchFragment(fragment)
            }
        }
        binding.rvTopMovies.adapter = topAdapter

        favoriteAdapter = FavoriteAdapter()
        favoriteAdapter.onMovieItemClickListener = object : FavoriteAdapter.OnItemClickListener {
            override fun onItemClick(movieItem: MovieItem) {
                val fragment = MovieDetailFragment.newInstance(movieItem.id)
                launchFragment(fragment)
            }
        }
        binding.rvMyFavouritesMovies.adapter = favoriteAdapter
    }

    private fun launchFragment(fragment: Fragment) {
        parentFragmentManager.popBackStack()
        parentFragmentManager.beginTransaction().replace(R.id.main_container, fragment)
            .addToBackStack(null).commit()
    }

    private fun setupObservers() {
        viewModel.popularMovieList.observe(requireActivity()) {
            popularAdapter.movieInfoList = it
        }
        viewModel.topMovieList.observe(requireActivity()) {
            topAdapter.topMovieInfoList = it
        }
        viewModel.favoriteMovieList.observe(requireActivity()) {
            favoriteAdapter.favoriteMovieInfoList = it
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}