package com.github.veselinazatchepina.cardcontent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.veselinazatchepina.cardcontent.domain.MovieUseCase
import com.github.veselinazatchepina.cardcontent.domain.MovieUseCaseImpl
import com.github.veselinazatchepina.entities.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CardContentViewModel : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val movieUseCase: MovieUseCase = MovieUseCaseImpl()
    val liveMovie = MutableLiveData<Movie>()

    fun getMovieById(movieId: Int) {
        compositeDisposable.add(movieUseCase.getMovieById(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                liveMovie.value = it
            }, {
                it.printStackTrace()
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}