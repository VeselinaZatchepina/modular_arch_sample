package com.github.veselinazatchepina.repositories.datasource.mapper

import com.github.veselinazatchepina.entities.Movie
import com.github.veselinazatchepina.repositories.datasource.local.db.MovieEntity
import com.github.veselinazatchepina.repositories.datasource.net.entities.MovieRemote

/**
 * Маппер для запросов в сеть - получение данных.
 */
fun fromMovieRemoteToMovie(movieRemote: MovieRemote): Movie {
    return Movie(
        movieRemote.id,
        movieRemote.title,
        movieRemote.poster_path,
        movieRemote.overview
    )
}

/**
 * Маппер для запросов в локальную бд - получение данных.
 */
fun fromMovieEntityToMovie(movieEntity: MovieEntity): Movie {
    return Movie(
        movieEntity.movieId,
        movieEntity.title,
        movieEntity.posterPath,
        movieEntity.overview
    )
}

/**
 * Маппер для запросов в локальную бд - сохранение данных.
 */
fun fromMovieToMovieEntity(movie: Movie): MovieEntity {
    return MovieEntity(
        movie.id ?: 0,
        movie.title ?: "",
        movie.posterPath ?: "",
        movie.overview ?: ""
    )
}