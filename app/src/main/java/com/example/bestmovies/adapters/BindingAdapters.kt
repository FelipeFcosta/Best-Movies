package com.example.bestmovies

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bestmovies.adapters.MovieListAdapter
import com.example.bestmovies.models.Movie
import com.example.bestmovies.models.ProductionCountry
import com.example.bestmovies.network.MoviesApi
import com.example.bestmovies.utilities.Constants
import java.text.NumberFormat
import java.util.*

@BindingAdapter("posterPath")
fun bindPoster(imgView: ImageView, imgPath: String?) {
    imgPath?.let {
        loadImage(imgView, Constants.BASE_POSTER_URL + imgPath)
    }
}

@BindingAdapter("backdropPath")
fun bindBackdrop(imgView: ImageView, imgPath: String?) {
    imgPath?.let {
        loadImage(imgView, Constants.BASE_BACKDROP_URL + imgPath)
    }
}

private fun loadImage(imgView: ImageView, imgUrl: String) {
    val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
    imgView.load(imgUri) {
        listener(onError = { _,_ ->
            Log.d("BindingAdapters", "Image load failure!")
        })
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
    var genres = ""
    genreIds?.let {
        for (id in genreIds) {
            genres += "${Constants.genresById[id]} • "
        }
    }
    textView.text = genres.dropLast(2)
}

@BindingAdapter("rating")
fun bindRating(textView: TextView, voteAverage: Double?) {
    voteAverage?.let {
        textView.text = "$it"
    }
}

@BindingAdapter("runtime")
fun bindRuntime(textView: TextView, runtime: Int?) {
    runtime?.let {
        val time = "${runtime/60}h ${runtime%60}m"
        textView.text = time
    }
}

@BindingAdapter("dollar_text")
fun bindDollarText(textView: TextView, quantity: Int?) {
    quantity?.let {
        textView.text = NumberFormat.getCurrencyInstance(Locale("en", "US"))
            .format(it)
    }
}

@BindingAdapter("countries")
fun bindCountries(textView: TextView, countries: List<ProductionCountry>?) {

    var countryNames = ""
    countries?.let {
        for (country in countries) {
            countryNames += "${country.name}, "
        }
    }
    textView.text = countryNames.dropLast(2)
}


@BindingAdapter("loadingStatus")
fun bindLoadingStatus(view: ProgressBar, status: MoviesApi.MovieStatus?) {
    makeVisibleWhen(MoviesApi.MovieStatus.LOADING, view, status)
}

@BindingAdapter("displayStatus")
fun bindDisplayStatus(view: View, status: MoviesApi.MovieStatus?) {
    makeVisibleWhen(MoviesApi.MovieStatus.DONE, view, status)
}

@BindingAdapter("errorStatus")
fun bindErrorStatus(view: View, status: MoviesApi.MovieStatus?) {
    makeVisibleWhen(MoviesApi.MovieStatus.ERROR, view, status)
}


/**
 * If the status is equal to the condition given, makes the [View] visible
 */
fun makeVisibleWhen(condition: MoviesApi.MovieStatus?, view: View, status: MoviesApi.MovieStatus?) {
    view.visibility = if (status == condition) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

