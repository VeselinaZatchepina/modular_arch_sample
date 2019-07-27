package com.github.veselinazatchepina.listcontent.navigator

import android.content.Context
import android.view.View
import com.github.veselinazatchepina.entities.Movie
import io.reactivex.Flowable
import io.reactivex.Single

interface ListContentNavigator {

    fun showCardContent(movieId: Int)

    fun showShortFilmInfo(context: Context, movie: Movie): View
}