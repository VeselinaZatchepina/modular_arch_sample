package com.github.veselinazatchepina.listcontent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.veselinazatchepina.entities.Movie
import com.github.veselinazatchepina.listcontent.abstracts.AdapterImpl
import com.github.veselinazatchepina.listcontent.navigator.ListContentNavigator
import kotlinx.android.synthetic.main.fragment_list_content.*
import kotlinx.android.synthetic.main.item_recycler_view_movie.view.*
import me.vponomarenko.injectionmanager.x.XInjectionManager


class ListContentFragment : Fragment() {

    private val navigation: ListContentNavigator by lazy {
        XInjectionManager.findComponent<ListContentNavigator>()
    }
    private val listContentViewModel by lazy {
        ViewModelProviders.of(this).get(ListContentViewModel::class.java)
    }
    private var moviesAdapter: AdapterImpl<Movie>? = null

    companion object {

        fun createInstance(): ListContentFragment {
            return ListContentFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        listContentViewModel.getPopularMovies()
        return inflater.inflate(R.layout.fragment_list_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defineMoviesAdapter()
        listContentViewModel.liveMovies.observe(this, Observer {
            moviesAdapter?.update(it.sortedBy { it.id })
        })
    }

    private fun defineMoviesAdapter() {
        moviesAdapter = AdapterImpl(emptyList(),
            R.layout.item_recycler_view_movie,
            R.layout.item_recycler_view_movie_empty, {

                this.itemContainer.removeAllViews()
                this.itemContainer.addView(navigation.showShortFilmInfo(context, it))

            }, { _, position ->

                navigation.showCardContent(moviesAdapter?.getListItems()?.get(position)?.id ?: -1)

            }, {

            })
        moviesRecyclerView.adapter = moviesAdapter
        moviesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}