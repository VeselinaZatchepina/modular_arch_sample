package com.github.veselinazatchepina.listcontent.domain

import com.github.veselinazatchepina.entities.Movie
import io.reactivex.Flowable

interface MovieUseCase {

    fun getPopularMovies(): Flowable<List<Movie>>
}