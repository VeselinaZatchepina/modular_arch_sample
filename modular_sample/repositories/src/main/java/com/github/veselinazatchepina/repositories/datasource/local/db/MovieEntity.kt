package com.github.veselinazatchepina.repositories.datasource.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
class MovieEntity(
    @PrimaryKey var movieId: Int,
    var title: String,
    var posterPath: String,
    var overview: String
)