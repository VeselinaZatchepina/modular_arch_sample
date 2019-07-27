package com.github.veselinazatchepina.listcontent.action

import com.github.veselinazatchepina.entities.Movie
import io.reactivex.Flowable

/**
 * Интерфейс для работы с данными.
 * Связующее звено между listcontent модулем и модулем repositories.
 */
interface ListContentAction {

    fun getPopularFilms(): Flowable<List<Movie>>
}