package com.nighttwo1.domain.model

import java.util.*

data class TMDBTrending(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val trendings: List<Trending>,
)

data class Trending(
    val id: Int,
    val posterPath: String,
    val date: Date,
    val title: String,
    val rating: Ratings,
    val overview: String,
    val mediaType: MediaType,
    val genres: List<GenreType>
)

enum class MediaType(name: String) {
    movie("movie"), tv("tv"), person("person")
}

enum class GenreType(val displayName: String, val id: Int) {
    Action("Action", 28),
    Action_Adventure("Action & Adventure", 10759),
    Adventure("Adventure", 12),
    Animation("Animation", 16),
    Comedy("Comedy", 35),
    Crime("Crime", 80),
    Documentary("Documentary", 99),
    Drama("Drama", 18),
    Family("Family", 10751),
    Fantasy("Fantasy", 14),
    History("History", 36),
    Horror("Horror", 27),
    Kids("Kids", 10762),
    Music("Music", 10402),
    Mystery("Mystery", 9648),
    News("News", 10763),
    Reality("Reality", 10764),
    Romance("Romance", 10749),
    Science_Fiction("Science Fiction", 878),
    Science_Fiction_Fantasy("Sci-Fi & Fantasy", 10765),
    Soap("Soap", 10766),
    Talk("Talk", 10767),
    TV_Movie("TV Movie", 10770),
    Thriller("Thriller", 53),
    War("War", 10752),
    War_Politics("War & Politics", 10768),
    Western("Western", 37);

    companion object {
        fun getById(id: Int): GenreType {
            return entries.find { it.id == id } ?: Action
        }
    }
}