package com.github.veselinazatchepina.cardcontent.action

import com.github.veselinazatchepina.entities.Movie
import io.reactivex.Single

/**
 * Интерфейс для работы с данными.
 * Связующее звено между cardcontent модулем и модулем repositories.
 */
interface CardContentAction {

    fun getMovieById(movieId: Int): Single<Movie>

}