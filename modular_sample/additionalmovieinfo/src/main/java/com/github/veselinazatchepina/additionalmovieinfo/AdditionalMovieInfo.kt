package com.github.veselinazatchepina.additionalmovieinfo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.github.veselinazatchepina.entities.Movie
import kotlinx.android.synthetic.main.fragment_additional_film_info.view.*

/**
 * Модуль описания(overview) фильма.
 */
class AdditionalMovieInfo {

    fun defineAdditionalFilmInfo(context: Context, movie: Movie): View {
        val layoutInflater = LayoutInflater.from(context)
        val currentLayout = layoutInflater.inflate(R.layout.fragment_additional_film_info, null)
        fillLayoutWithMovieData(currentLayout, movie)
        return currentLayout
    }

    private fun fillLayoutWithMovieData(currentLayout: View, movie: Movie) {
        currentLayout.movieDescription.text = movie.overview
    }

}