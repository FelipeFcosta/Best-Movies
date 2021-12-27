package com.example.bestmovies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bestmovies.databinding.FragmentDetailedMovieBinding
import com.example.bestmovies.network.MoviesApi
import com.example.bestmovies.viewmodels.MoviesViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [DetailedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailedMovieFragment : Fragment() {

    // Binding object instance corresponding to the fragment_top_movies.xml layout
    private var binding: FragmentDetailedMovieBinding? = null

    private val viewModel: MoviesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentBinding = FragmentDetailedMovieBinding.inflate(inflater, container, false)

        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            detailedMovieFragment = this@DetailedMovieFragment
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@DetailedMovieFragment.viewModel
            this@DetailedMovieFragment.viewModel.getDetailedMovie()

            includedConnectionErrorLayout.buttonTryAgain.setOnClickListener {
                this@DetailedMovieFragment.viewModel.repeatMostRecentRequest()
            }
        }
    }
}