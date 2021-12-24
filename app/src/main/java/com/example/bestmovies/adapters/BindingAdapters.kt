package com.example.bestmovies

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bestmovies.adapters.MovieListAdapter
import com.example.bestmovies.models.Movie
import com.example.bestmovies.utilities.Constants

@BindingAdapter("posterPath")
fun bindPoster(imgView: ImageView, imgPath: String?) {
    imgPath?.let {
        val imgUrl = Constants.BASE_POSTER_URL + imgPath
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            listener(onError = { _,_ ->
                Log.d("BindingAdapters", "Image load failure!")
            })
        }
    }
}

// makes data binding automatically observe the LiveData for the list of Movie objects.
// Then the binding adapter is called automatically when the Movie list changes.
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieListAdapter
    adapter.submitList(data)
}

@BindingAdapter("genres")
fun bindGenres(textView: TextView, genreIds: List<Int>?) {
    var genres: String = ""
    genreIds?.let {
        for (id in genreIds) {
            genres += "${Constants.genresById[id]} • "
        }
    }
    textView.text = genres.dropLast(2)
}
