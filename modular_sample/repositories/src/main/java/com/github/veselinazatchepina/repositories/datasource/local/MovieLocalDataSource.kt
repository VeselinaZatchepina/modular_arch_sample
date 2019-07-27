package com.github.veselinazatchepina.repositories.datasource.local

import com.github.veselinazatchepina.entities.Movie
import com.github.veselinazatchepina.repositories.datasource.MovieDataSource
import com.github.veselinazatchepina.repositories.datasource.local.db.MovieDatabase
import com.github.veselinazatchepina.repositories.datasource.local.db.MovieEntity
import com.github.veselinazatchepina.repositories.datasource.mapper.fromMovieEntityToMovie
import com.github.veselinazatchepina.repositories.datasource.mapper.fromMovieToMovieEntity
import io.reactivex.Completable
import io.reactivex.Single

class MovieLocalDataSource(private val movieDatabase: MovieDatabase) : MovieDataSource {

    override fun getPopularMovies(): Single<List<Movie>> {
        return movieDatabase.movieDao()
            .getPopularMovies()
            .map { list -> list.map { fromMovieEntityToMovie(it) } }
    }

    override fun getMovieById(movieId: Int): Single<Movie> {
        return movieDatabase.movieDao()
            .getMovieById(movieId)
            .map { fromMovieEntityToMovie(it) }
    }

    override fun saveMovies(movies: List<Movie>): Completable {
        return movieDatabase.movieDao()
            .saveMovies(movies.map { fromMovieToMovieEntity(it) })
    }
}