package com.github.veselinazatchepina.repositories.datasource.net

import com.github.veselinazatchepina.repositories.datasource.net.entities.MovieRemote
import com.github.veselinazatchepina.repositories.datasource.net.entities.MoviesPage
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiInterface {

    @GET("popular")
    fun getPopularFilms(): Single<MoviesPage>

    @GET("{movie_id}")
    fun getMovieById(@Path("movie_id") movieId: Int): Single<MovieRemote>

}