package com.github.veselinazatchepina.modulararchitecturesample.navigator

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.github.veselinazatchepina.additionalmovieinfo.AdditionalMovieInfo
import com.github.veselinazatchepina.cardcontent.CardContentFragment
import com.github.veselinazatchepina.entities.Movie
import com.github.veselinazatchepina.listcontent.ListContentFragment
import com.github.veselinazatchepina.modulararchitecturesample.R
import com.github.veselinazatchepina.repositories.MovieRepositoryImpl
import com.github.veselinazatchepina.shortmovieinfo.ShortMovieInfo
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Класс для работы с экранами.
 * Связующее звено между cardcontent, listcontent, app модулями и модулями shortmovieinfo, additionalmovieinfo.
 */
class NavigatorImpl : Navigator {

    private var activity: FragmentActivity? = null

    override fun bind(activity: FragmentActivity?) {
        this.activity = activity
    }

    override fun unbind() {
        activity = null
    }

    override fun showListContent() {
        replaceFragment(activity, ListContentFragment.createInstance())
    }

    override fun showCardContent(movieId: Int) {
        replaceFragment(activity, CardContentFragment.createInstance(movieId))
    }

    override fun showShortFilmInfo(context: Context, movie: Movie) = ShortMovieInfo().defineShortMovieInfo(context, movie)

    override fun showAdditionalFilmInfo(context: Context, movie: Movie) = AdditionalMovieInfo().defineAdditionalFilmInfo(context, movie)

    private fun replaceFragment(activity: FragmentActivity?, fragment: Fragment?) {
        if (fragment != null) {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.rootContainer, fragment)
                ?.addToBackStack("")
                ?.commit()
        }
    }
}