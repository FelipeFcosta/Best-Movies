package com.example.bestmovies.topmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.bestmovies.databinding.FragmentTopMoviesBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TopMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopMoviesFragment : Fragment() {

    // Binding object instance corresponding to the fragment_top_movies.xml layout
    private var binding: FragmentTopMoviesBinding? = null

    private val viewModel: TopMoviesViewModel by viewModels()

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
        }
    }
}