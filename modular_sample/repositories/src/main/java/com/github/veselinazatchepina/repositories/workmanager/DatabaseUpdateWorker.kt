package com.github.veselinazatchepina.repositories.workmanager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.github.veselinazatchepina.repositories.MovieRepositoryImpl
import io.reactivex.disposables.CompositeDisposable

/**
 * Класс выполняет работу по обновлению локальной базы данных.
 */
class DatabaseUpdateWorker(
    private val context: Context,
    parameters: WorkerParameters
) : Worker(context, parameters) {

    private val compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun doWork(): Result {
        compositeDisposable.add(MovieRepositoryImpl(context).updatePopularMovies().subscribe({
        }, {
            it.printStackTrace()
        }))
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        compositeDisposable.clear()
    }
}