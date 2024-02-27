package com.nighttwo1.data.service

import com.nighttwo1.data.model.TMDBMovies
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("search/movie")
    suspend fun getSearchedMovie(
        @Query("query") searchedText: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): TMDBMovies
}