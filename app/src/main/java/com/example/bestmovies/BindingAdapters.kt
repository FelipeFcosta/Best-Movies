package com.example.bestmovies

import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bestmovies.adapters.MovieListAdapter
import com.example.bestmovies.models.Movie

@BindingAdapter("app:posterPath")
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
@BindingAdapter("app:listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieListAdapter
    adapter.submitList(data)
}
