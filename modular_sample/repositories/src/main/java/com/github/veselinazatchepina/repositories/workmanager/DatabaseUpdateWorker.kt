package com.github.veselinazatchepina.repositories.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.github.veselinazatchepina.repositories.MovieRepositoryImpl

/**
 * Класс выполняет работу по обновлению локальной базы данных.
 */
class DatabaseUpdateWorker(private val context: Context,
                           parameters: WorkerParameters) : Worker(context, parameters) {

    override fun doWork(): Result {
        MovieRepositoryImpl(context).updatePopularMovies().subscribe()
        return Result.success()
    }
}