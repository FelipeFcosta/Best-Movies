package com.example.bestmovies.topmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bestmovies.databinding.FragmentTopMoviesBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TopMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopMoviesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentTopMoviesBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        return binding.root
    }
}