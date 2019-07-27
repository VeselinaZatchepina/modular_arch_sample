package com.github.veselinazatchepina.shortmovieinfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.github.veselinazatchepina.entities.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_short_film_info.view.*

/**
 * Модуль постера и названия фильма.
 */
class ShortMovieInfo {

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
    }

    fun defineShortMovieInfo(context: Context, movie: Movie): View {
        val layoutInflater = LayoutInflater.from(context)
        val currentLayout = layoutInflater.inflate(R.layout.fragment_short_film_info, null)
        fillLayoutWithMovieData(currentLayout, movie)
        return currentLayout
    }

    private fun fillLayoutWithMovieData(
        currentLayout: View,
        movie: Movie
    ) {
        currentLayout.movieTitleText.text = movie.title
        if (!movie.posterPath.isNullOrEmpty()) {
            Picasso.get()
                .load("$IMAGE_BASE_URL${movie.posterPath}")
                .fit()
                .centerCrop()
                .into(currentLayout.moviePosterImage)
        }
    }
}