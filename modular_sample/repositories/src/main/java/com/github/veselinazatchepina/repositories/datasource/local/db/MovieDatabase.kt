package com.github.veselinazatchepina.repositories.datasource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MovieEntity::class), version = 1)
abstract class MovieDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase? {
            if (INSTANCE == null) {
                synchronized(MovieDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        MovieDatabase::class.java, "movies_db")
                        .build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun movieDao(): MovieDao

}