package com.github.veselinazatchepina.cardcontent.domain

import com.github.veselinazatchepina.entities.Movie
import io.reactivex.Single


interface MovieUseCase {

    fun getMovieById(movieId: Int): Single<Movie>
}