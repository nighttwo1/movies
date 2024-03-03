package com.nighttwo1.domain.model

import java.text.DecimalFormat
import java.time.Duration
import java.util.*


data class TMDBMovies(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val movieList: List<Movie>
)

@JvmInline
value class MovieId(val value: Int)

@JvmInline
value class Ratings(val value: Double){
    override fun toString(): String {
        val formatter = DecimalFormat("0.0")

        return formatter.format(value)
    }
}

data class Movie(
    val id: MovieId,
    val posterPath: String,
    val releaseDate: Date,
    val title: String,
    val rating: Ratings,
)

data class MovieDetail(
    val id: MovieId,
    val genres: List<Genres>,
    val backdropPath: String,
    val posterPath: String,
    val releaseDate: Date,
    val title: String,
    val overview: String,
    val rating: Ratings,
    val runtime: Duration,
)

@JvmInline
value class GenreId(val value: Int)
data class Genres(
    val id: GenreId,
    val name: String,
)