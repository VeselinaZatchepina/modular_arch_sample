package com.github.veselinazatchepina.repositories.datasource.net

import com.github.veselinazatchepina.entities.Movie
import com.github.veselinazatchepina.repositories.datasource.MovieDataSource
import com.github.veselinazatchepina.repositories.datasource.mapper.fromMovieRemoteToMovie
import io.reactivex.Completable
import io.reactivex.Single

class MovieRemoteDataSource(private val movieApi: MovieApiInterface) : MovieDataSource {

    override fun getPopularMovies(): Single<List<Movie>> {
        return movieApi.getPopularFilms()
            .map { moviesPage -> moviesPage.results?.map { fromMovieRemoteToMovie(it) } }
    }

    override fun getMovieById(movieId: Int): Single<Movie> {
        return movieApi.getMovieById(movieId)
            .map { fromMovieRemoteToMovie(it) }
    }

    override fun saveMovies(movies: List<Movie>): Completable {
        TODO("not implemented")
    }
}