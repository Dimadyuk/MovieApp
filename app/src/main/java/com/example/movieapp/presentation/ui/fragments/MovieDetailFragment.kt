package com.example.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.domain.MovieItem
import com.example.movieapp.presentation.ui.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class MovieDetailFragment : Fragment() {
    private val binding by lazy {
        FragmentMovieDetailBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: MainViewModel
    private var movieId: Int = MovieItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(EXTRA_MOVIE_ITEM_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        lifecycleScope.launch {
            val movieItem = viewModel.getMovieItem(movieId)
            setValues(movieItem)
        }

    }

    private fun setValues(movieItem: MovieItem) {
        binding.movieTitle.text = movieItem.name
        binding.movieDescription.text = movieItem.overview
        Picasso.get().load(movieItem.imageUrl).into(binding.ivLogo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    companion object {
        private const val EXTRA_MOVIE_ITEM_ID = "extra_movie_item_id"
        fun newInstance(movieId: Int) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_MOVIE_ITEM_ID, movieId)
                }
            }
    }
}