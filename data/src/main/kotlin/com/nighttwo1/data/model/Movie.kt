package com.nighttwo1.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class TMDBMovies(
    val page: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int,
    @SerialName("results")
    val movieList: List<Movie>,
)

@Serializable
data class Movie(
    val id: Int,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String,
    val title: String,
    @SerialName("vote_average")
    val rating: Double
)

fun TMDBMovies.toDomain(): com.nighttwo1.domain.model.TMDBMovies = com.nighttwo1.domain.model.TMDBMovies(
    page = page,
    totalPages = totalPages,
    totalResults = totalResults,
    movieList = movieList.map {
        com.nighttwo1.domain.model.Movie(
            id = it.id,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            rating = it.rating
        )
    }
)