package com.github.veselinazatchepina.repositories.datasource.net.entities


data class MoviesPage(var page: Int? = 0,
                      var total_results: Int? = 0,
                      var total_pages: Int? = 0,
                      var results: List<MovieRemote>? = emptyList())