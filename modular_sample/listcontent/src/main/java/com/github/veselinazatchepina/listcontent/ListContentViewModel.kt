package com.github.veselinazatchepina.listcontent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.veselinazatchepina.entities.Movie
import com.github.veselinazatchepina.listcontent.domain.MovieUseCase
import com.github.veselinazatchepina.listcontent.domain.MovieUseCaseImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListContentViewModel : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val movieUseCase: MovieUseCase = MovieUseCaseImpl()
    val liveMovies = MutableLiveData<List<Movie>>()

    fun getPopularMovies() {
        compositeDisposable.add(movieUseCase.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveMovies.value = it
            }, {
                it.printStackTrace()
            }))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}