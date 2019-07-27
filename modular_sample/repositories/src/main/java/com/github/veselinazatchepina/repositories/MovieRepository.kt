package com.github.veselinazatchepina.repositories

import com.github.veselinazatchepina.entities.Movie
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface MovieRepository {

    fun getPopularMovies(): Flowable<List<Movie>>

    fun updatePopularMovies(): Completable

    fun getMovieById(movieId: Int): Single<Movie>
}