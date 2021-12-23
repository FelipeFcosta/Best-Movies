package com.example.bestmovies.detailedmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bestmovies.databinding.FragmentDetailedMovieBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DetailedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailedMovieFragment : Fragment() {

    // Binding object instance corresponding to the fragment_top_movies.xml layout
    private var binding: FragmentDetailedMovieBinding? = null

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
        }
    }
}