package com.github.veselinazatchepina.repositories

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import com.github.veselinazatchepina.entities.Movie
import com.github.veselinazatchepina.repositories.datasource.local.MovieLocalDataSource
import com.github.veselinazatchepina.repositories.datasource.local.db.MovieDatabase
import com.github.veselinazatchepina.repositories.datasource.net.MovieRemoteDataSource
import com.github.veselinazatchepina.repositories.datasource.net.MovieRetrofit
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.github.veselinazatchepina.repositories.workmanager.DatabaseUpdateWorker
import java.util.concurrent.TimeUnit


class MovieRepositoryImpl(context: Context) : MovieRepository {

    private val localDataSource: MovieLocalDataSource = MovieLocalDataSource(MovieDatabase.getInstance(context)!!)
    private val remoteDataSource: MovieRemoteDataSource = MovieRemoteDataSource(MovieRetrofit.movieApi)

    companion object {
        private const val UNIQUE_DB_WORKER_NAME = "UNIQUE_DB_WORKER_NAME"
    }

    init {
        initDatabaseUpdaterWorker()
    }

    override fun getPopularMovies(): Flowable<List<Movie>> {
        return remoteDataSource.getPopularMovies()
            .doOnSuccess {
                localDataSource.saveMovies(it)
                    .subscribeOn(Schedulers.io())
                    .subscribe()
            }
            .onErrorResumeNext(
                localDataSource.getPopularMovies()
                    .onErrorReturnItem(emptyList())
            )
            .toFlowable()
    }

    override fun getMovieById(movieId: Int): Single<Movie> {
        return localDataSource.getMovieById(movieId)
    }

    override fun updatePopularMovies(): Completable {
        return remoteDataSource.getPopularMovies()
            .flatMapCompletable {
                localDataSource.saveMovies(it)
            }
            .onErrorComplete()
    }

    /**
     * Инициализация [WorkManager] для повторящегося процесса обновления локальной базы данных.
     */
    private fun initDatabaseUpdaterWorker() {
        val workManager = WorkManager.getInstance()
        val callDataRequest = PeriodicWorkRequest.Builder(
            DatabaseUpdateWorker::class.java,
            2,
            TimeUnit.HOURS
        )
            .build()
        workManager.enqueueUniquePeriodicWork(UNIQUE_DB_WORKER_NAME, ExistingPeriodicWorkPolicy.KEEP, callDataRequest)
    }
}