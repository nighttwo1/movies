package com.nighttwo1.domain.model

import java.time.Duration
import java.util.*

data class TMDBTvSeries(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val tvSeriesList: List<TvSeries>,
)

@JvmInline
value class TvId(val value: Int)

data class TvSeries(
    val id: TvId,
    val name: String,
    val posterPath: String,
    val backdropPath: String,
    val rating: Ratings,
    val firstAirDate: Date,
)

data class TvDetail(
    val id: TvId,
    val name: String,
    val originalName: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val seasons: List<Season>,
    val rating: Ratings,
    val type: String,
)

@JvmInline
value class SeasonId(val value: Int)

data class Season(
    val id: SeasonId,
    val name: String,
    val posterPath: String,
    val seasonNumber: Int,
    val episodeCount: Int,
    val airDate: Date,
)

data class SeasonDetail(
    val id: SeasonId,
    val name: String,
    val seasonNumber: Int,
    val episodes: List<Episode>
)

@JvmInline
value class EpisodeId(val value: Int)

data class Episode(
    val id: EpisodeId,
    val name: String,
    val overview: String,
    val airDate: Date,
    val episodeNumber: Int,
    val runtime: Duration,
    val stillPath: String
)