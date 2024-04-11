package com.nighttwo1.data.model

import com.nighttwo1.domain.model.EpisodeId
import com.nighttwo1.domain.model.Ratings
import com.nighttwo1.domain.model.SeasonId
import com.nighttwo1.domain.model.TvId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.time.Duration

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

@Serializable
data class TvDetail(
    val id: Int,
    val name: String,
    @SerialName("original_name")
    val originalName: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    val seasons: List<Season>,
    val type: String,
    @SerialName("vote_average")
    val rating: Double,
)

@Serializable
data class Season(
    val id: Int,
    val name: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("season_number")
    val seasonNumber: Int,
    @SerialName("episode_count")
    val episodeCount: Int,
    @SerialName("air_date")
    val airDate: String,
)

@Serializable
data class SeasonDetail(
    val id: Int,
    val name: String,
    @SerialName("season_number")
    val seasonNumber: Int,
    val episodes: List<Episode>
)

@Serializable
data class Episode(
    val id: Int,
    val name: String,
    val overview: String,
    @SerialName("air_date")
    val airDate: String,
    @SerialName("episode_number")
    val episodeNumber: Int,
    val runtime: Int,
    @SerialName("still_path")
    val stillPath: String?,
)

fun TMDBTVSeries.toDomain() = com.nighttwo1.domain.model.TMDBTvSeries(
    page = page,
    totalPages = totalPages,
    totalResults = totalResults,
    tvSeriesList = tvSeriesList.map { tvSeries ->
        tvSeries.toDomain()
    }
)

private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-mm-dd")
fun TVSeries.toDomain() = com.nighttwo1.domain.model.TvSeries(
    id = TvId(id),
    name = name,
    posterPath = posterPath,
    backdropPath = backdropPath ?: "",
    rating = Ratings(rating),
    firstAirDate = dateFormat.parse(firstAirDate)!!,
)

fun TvDetail.toDomain() = com.nighttwo1.domain.model.TvDetail(
    id = TvId(id),
    name = name,
    posterPath = posterPath,
    backdropPath = backdropPath ?: "",
    originalName = originalName,
    overview = overview,
    type = type,
    rating = Ratings(rating),
    seasons = seasons.map {
        it.toDomain()
    }
)

fun Season.toDomain() = com.nighttwo1.domain.model.Season(
    id = SeasonId(id),
    name = name,
    posterPath = posterPath,
    seasonNumber = seasonNumber,
    episodeCount = episodeCount,
    airDate = dateFormat.parse(airDate)!!
)

fun SeasonDetail.toDomain() = com.nighttwo1.domain.model.SeasonDetail(
    id = SeasonId(id),
    name = name,
    seasonNumber = seasonNumber,
    episodes = episodes.map {
        it.toDomain()
    }
)

fun Episode.toDomain() = com.nighttwo1.domain.model.Episode(
    id = EpisodeId(id),
    name = name,
    overview = overview,
    airDate = dateFormat.parse(airDate)!!,
    episodeNumber = episodeNumber,
    runtime = Duration.ofMinutes(runtime.toLong()),
    stillPath = stillPath ?: ""
)