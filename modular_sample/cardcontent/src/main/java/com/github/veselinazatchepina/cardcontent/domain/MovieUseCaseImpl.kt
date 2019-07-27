package com.github.veselinazatchepina.cardcontent.domain

import com.github.veselinazatchepina.cardcontent.action.CardContentAction
import com.github.veselinazatchepina.cardcontent.navigator.CardContentNavigator
import com.github.veselinazatchepina.entities.Movie
import io.reactivex.Single
import me.vponomarenko.injectionmanager.x.XInjectionManager

class MovieUseCaseImpl : MovieUseCase {

    /**
     * Переменная является связующим звеном между cardcontent модулем и модулем repositories.
     */
    private val action: CardContentAction by lazy {
        XInjectionManager.findComponent<CardContentAction>()
    }

    override fun getMovieById(movieId: Int): Single<Movie> {
        return action.getMovieById(movieId)
    }
}