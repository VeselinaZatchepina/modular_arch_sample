package com.github.veselinazatchepina.repositories.datasource.net.entities

import java.io.Serializable

data class MovieRemote(
    var id: Int? = 0,
    var title: String? = "",
    var poster_path: String? = "",
    var overview: String? = "") : Serializable