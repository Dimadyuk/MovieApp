package com.example.movieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.databinding.FragmentMainBinding
import com.example.movieapp.presentation.ui.MainViewModel

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var mainAdapter: MainAdapter
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
        mainAdapter = MainAdapter()
        binding.rvPopularMovies.adapter = mainAdapter
        viewModel.movieList.observe(requireActivity()) {
            mainAdapter.movieInfoList = it
        }
        viewModel.loadData()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}