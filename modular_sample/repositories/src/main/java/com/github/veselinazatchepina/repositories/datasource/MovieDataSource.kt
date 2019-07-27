package com.github.veselinazatchepina.repositories.datasource

import com.github.veselinazatchepina.entities.Movie
import io.reactivex.Completable
import io.reactivex.Single

interface MovieDataSource {

    fun getPopularMovies(): Single<List<Movie>>

    fun getMovieById(movieId: Int): Single<Movie>

    fun saveMovies(movies: List<Movie>): Completable
}