package com.nighttwo1.data.model

import com.nighttwo1.domain.model.GenreId
import com.nighttwo1.domain.model.MovieId
import com.nighttwo1.domain.model.Ratings
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.time.Duration

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
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String,
    val title: String,
    @SerialName("vote_average")
    val rating: Double,
    val overview: String
)

@Serializable
data class MovieDetail(
    val id: Int,
    val genres: List<Genres>,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("release_date")
    val releaseDate: String,
    val title: String,
    val overview: String,
    @SerialName("vote_average")
    val rating: Double,
    val runtime: Int,
)

@Serializable
data class Genres(
    val id: Int,
    val name: String,
)

@Serializable
data class MovieAccountStates(
    val id: Int,
    val favorite: Boolean,
    val rated: Boolean,
    val watchlist: Boolean
)

fun TMDBMovies.toDomain(): com.nighttwo1.domain.model.TMDBMovies = com.nighttwo1.domain.model.TMDBMovies(
    page = page,
    totalPages = totalPages,
    totalResults = totalResults,
    movieList = movieList.map {
        it.toDomain()
    }
)

private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-mm-dd")

fun Movie.toDomain() = com.nighttwo1.domain.model.Movie(
    id = MovieId(id),
    posterPath = posterPath?: "",
    releaseDate = dateFormat.parse(releaseDate)!!,
    title = title,
    rating = Ratings(rating),
    overview = overview
)

fun MovieDetail.toDomain() = com.nighttwo1.domain.model.MovieDetail(
    id = MovieId(id),
    genres = genres.map {
        com.nighttwo1.domain.model.Genres(GenreId(it.id), it.name)
    },
    backdropPath = backdropPath,
    posterPath = posterPath ?: "",
    releaseDate = dateFormat.parse(releaseDate)!!,
    title = title,
    overview = overview,
    rating = Ratings(rating),
    runtime = Duration.ofMinutes(runtime.toLong())
)

fun MovieAccountStates.toDomain() = com.nighttwo1.domain.model.MovieAccountStates(
    id = MovieId(id),
    favorite = favorite,
    rated = rated,
    watchlist = watchlist
)