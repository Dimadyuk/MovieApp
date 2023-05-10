package com.example.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.domain.MovieItem
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailFragment : Fragment() {
    private val binding by lazy {
        FragmentMovieDetailBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: DetailViewModel
    private var movieId: Int = MovieItem.UNDEFINED_ID


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(EXTRA_MOVIE_ITEM_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[DetailViewModel::class.java]
        lifecycleScope.launch {
            viewModel.getMovieItem(movieId)
        }
        viewModel.movieItem.observe(requireActivity()) {
            setValues(it)
        }

    }

    private fun setClickListeners(isFavorite: Boolean) {
        binding.btnAddToFavorites.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                if (isFavorite) {
                    viewModel.deleteFavoriteMovieItemUseCase.invoke(movieId)
                } else {
                    viewModel.addFavoriteMovieItemUseCase.invoke(movieId)
                }
                viewModel.getMovieItem(movieId)
            }
        }
        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private fun setValues(movieItem: MovieItem) {
        binding.movieTitle.text = movieItem.originalTitle
        binding.movieDescription.text = movieItem.overview
        setFavoriteIcon(movieItem.isFavorite)
        setClickListeners(movieItem.isFavorite)
        Picasso.get().load(movieItem.posterPath).into(binding.ivLogo)
    }

    private fun setFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite) {
            binding.btnAddToFavorites.setImageResource(R.drawable.is_favorite)
        } else {
            binding.btnAddToFavorites.setImageResource(R.drawable.is_not_favorite)
        }
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