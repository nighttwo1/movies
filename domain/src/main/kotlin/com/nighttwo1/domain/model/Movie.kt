package com.nighttwo1.domain.model

data class TMDBMovies(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val movieList: List<Movie>
)

data class Movie(
    val id: Int,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val rating: Ratings,
)

@JvmInline
value class Ratings(val value: Double)
