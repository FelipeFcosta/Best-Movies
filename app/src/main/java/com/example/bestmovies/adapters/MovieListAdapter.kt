package com.example.bestmovies.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bestmovies.databinding.MovieItemViewBinding
import com.example.bestmovies.models.Movie

class MovieListAdapter : ListAdapter<Movie,
        MovieListAdapter.ListMoviesViewHolder>(DiffCallBack) {


    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [Movie] has been updated.
     */
    object DiffCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title && oldItem.posterPath == newItem.posterPath
        }
    }

    class ListMoviesViewHolder(
        private var binding: MovieItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.textTitle.text = movie.title

            // force the data binding to execute immediately
            binding.executePendingBindings()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMoviesViewHolder {
        return ListMoviesViewHolder(MovieItemViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ListMoviesViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }


}