package com.github.veselinazatchepina.listcontent.domain

import com.github.veselinazatchepina.entities.Movie
import com.github.veselinazatchepina.listcontent.action.ListContentAction
import com.github.veselinazatchepina.listcontent.navigator.ListContentNavigator
import io.reactivex.Flowable
import io.reactivex.Single
import me.vponomarenko.injectionmanager.x.XInjectionManager

class MovieUseCaseImpl : MovieUseCase {

    /**
     * Переменная является связующим звеном между listcontent модулем и модулем repositories.
     */
    private val action: ListContentAction by lazy {
        XInjectionManager.findComponent<ListContentAction>()
    }

    override fun getPopularMovies(): Flowable<List<Movie>> {
        return action.getPopularFilms()
    }
}