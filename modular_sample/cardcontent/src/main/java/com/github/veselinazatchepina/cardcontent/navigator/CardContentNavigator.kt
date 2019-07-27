package com.github.veselinazatchepina.cardcontent.navigator

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.github.veselinazatchepina.entities.Movie
import io.reactivex.Single

/**
 * Интерфейс для работы с экранами.
 * Связующее звено между cardcontent модулем и модулями shortmovieinfo, additionalmovieinfo.
 */
interface CardContentNavigator {

    fun showShortFilmInfo(context: Context, movie: Movie): View

    fun showAdditionalFilmInfo(context: Context, movie: Movie): View

}