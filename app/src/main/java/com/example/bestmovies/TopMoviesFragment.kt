package com.example.bestmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.bestmovies.adapters.MovieClickListener
import com.example.bestmovies.adapters.MovieListAdapter
import com.example.bestmovies.databinding.FragmentTopMoviesBinding
import com.example.bestmovies.viewmodels.MoviesViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [TopMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopMoviesFragment : Fragment(), MovieClickListener {

    // Binding object instance corresponding to the fragment_top_movies.xml layout
    private var binding: FragmentTopMoviesBinding? = null

    private val viewModel: MoviesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentTopMoviesBinding.inflate(inflater, container, false)

        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            topMoviesFragment = this@TopMoviesFragment
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@TopMoviesFragment.viewModel
            moviesList.adapter = MovieListAdapter(this@TopMoviesFragment)
        }
    }

    override fun onClick(movieId: Int) {
        viewModel.setNewMovieId(movieId)
        findNavController().navigate(R.id.action_topMoviesFragment_to_detailedMovieFragment)
    }
}