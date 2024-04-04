package com.nighttwo1.data.model

import com.nighttwo1.domain.model.Ratings
import com.nighttwo1.domain.model.TVId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Serializable
data class TMDBTVSeries(
    val page: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int,
    @SerialName("results")
    val tvSeriesList: List<TVSeries>,
)


@Serializable
data class TVSeries(
    val id: Int,
    val name: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("vote_average")
    val rating: Double,
    @SerialName("first_air_date")
    val firstAirDate: String,
)

fun TMDBTVSeries.toDomain() = com.nighttwo1.domain.model.TMDBTVSeries(
    page = page,
    totalPages = totalPages,
    totalResults = totalResults,
    tvSeriesList = tvSeriesList.map { tvSeries ->
        tvSeries.toDomain()
    }
)

private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-mm-dd")
fun TVSeries.toDomain() = com.nighttwo1.domain.model.TVSeries(
    id = TVId(id),
    name = name,
    posterPath = posterPath,
    backdropPath = backdropPath ?: "",
    rating = Ratings(rating),
    firstAirDate = dateFormat.parse(firstAirDate)!!,
)