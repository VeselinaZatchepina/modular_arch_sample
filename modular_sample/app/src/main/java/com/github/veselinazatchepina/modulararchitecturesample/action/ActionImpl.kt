package com.github.veselinazatchepina.modulararchitecturesample.action

import android.content.Context
import com.github.veselinazatchepina.cardcontent.action.CardContentAction
import com.github.veselinazatchepina.entities.Movie
import com.github.veselinazatchepina.listcontent.action.ListContentAction
import com.github.veselinazatchepina.repositories.MovieRepositoryImpl
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Класс для работы с данными.
 * Связующее звено между cardcontent, listcontent модулями и модулем repositories.
 */
class ActionImpl(private val applicationContext: Context) : Action, CardContentAction, ListContentAction {

    override fun getMovieById(movieId: Int): Single<Movie> {
        return MovieRepositoryImpl(applicationContext).getMovieById(movieId)
    }

    override fun getPopularFilms(): Flowable<List<Movie>> {
        return MovieRepositoryImpl(applicationContext).getPopularMovies()
    }
}