package com.github.veselinazatchepina.cardcontent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.veselinazatchepina.cardcontent.navigator.CardContentNavigator
import kotlinx.android.synthetic.main.fragment_card_content.*
import me.vponomarenko.injectionmanager.x.XInjectionManager


class CardContentFragment : Fragment() {

    private val navigation: CardContentNavigator by lazy {
        XInjectionManager.findComponent<CardContentNavigator>()
    }
    private val cardContentViewModel by lazy {
        ViewModelProviders.of(this).get(CardContentViewModel::class.java)
    }
    private val movieId by lazy {
        arguments?.getInt(BUNDLE_MOVIE_ID, -1) ?: -1
    }

    companion object {
        private const val BUNDLE_MOVIE_ID = "BUNDLE_MOVIE_ID"

        fun createInstance(movieId: Int): CardContentFragment {
            val bundle = Bundle()
            bundle.putInt(BUNDLE_MOVIE_ID, movieId)
            val fragment = CardContentFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        cardContentViewModel.getMovieById(movieId)
        return inflater.inflate(R.layout.fragment_card_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cardContentViewModel.liveMovie.observe(this, Observer { movie ->
            context?.let {
                shortInfoContainer.addView(navigation.showShortFilmInfo(it, movie))
                additionalInfoContainer.addView(navigation.showAdditionalFilmInfo(it, movie))
            }
        })
    }

}