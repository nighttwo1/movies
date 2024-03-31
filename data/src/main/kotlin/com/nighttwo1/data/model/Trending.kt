package com.nighttwo1.data.model

import com.nighttwo1.domain.model.GenreType
import com.nighttwo1.domain.model.MediaType
import com.nighttwo1.domain.model.MovieId
import com.nighttwo1.domain.model.Ratings
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat

@Serializable
data class TMDBTrending(
    val page: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int,
    @SerialName("results")
    val trendings: List<Trending>,
)

@Serializable
data class Trending(
    val id: Int,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String,
    val title: String,
    @SerialName("vote_average")
    val rating: Double,
    val overview: String,
    @SerialName("media_type")
    val mediaType: String,
    @SerialName("genre_ids")
    val genres: List<Int>,
)

fun TMDBTrending.toDomain(): com.nighttwo1.domain.model.TMDBTrending = com.nighttwo1.domain.model.TMDBTrending(
    page = page,
    totalPages = totalPages,
    totalResults = totalResults,
    trendings = trendings.map {
        it.toDomain()
    }
)

private val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-mm-dd")

fun Trending.toDomain() = com.nighttwo1.domain.model.Trending(
    id = id,
    posterPath = posterPath,
    releaseDate = dateFormat.parse(releaseDate)!!,
    title = title,
    rating = Ratings(rating),
    overview = overview,
    mediaType = MediaType.valueOf(mediaType),
    genres = genres.map {
        GenreType.getById(it)
    }
)