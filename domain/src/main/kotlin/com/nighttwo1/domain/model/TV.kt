package com.nighttwo1.domain.model

import java.util.Date

data class TMDBTVSeries(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val tvSeriesList: List<TVSeries>,
)

@JvmInline
value class TVId(val id: Int)

data class TVSeries(
    val id: TVId,
    val name: String,
    val posterPath: String,
    val backdropPath: String,
    val rating: Ratings,
    val firstAirDate: Date,
)