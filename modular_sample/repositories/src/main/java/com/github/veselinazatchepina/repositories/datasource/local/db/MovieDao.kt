package com.github.veselinazatchepina.repositories.datasource.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.github.veselinazatchepina.entities.Movie
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("SELECT * from movies WHERE movieId = :id")
    fun getMovieById(id: Int): Single<MovieEntity>

    @Insert(onConflict = REPLACE)
    fun saveMovies(movies: List<MovieEntity>): Completable

    @Query("SELECT * from movies")
    fun getPopularMovies(): Single<List<MovieEntity>>

}