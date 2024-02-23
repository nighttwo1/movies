package com.nighttwo1.data.service

import com.nighttwo1.data.model.TMDBMovies
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("page") page: Int,
        @Query("region") region: String
    ): TMDBMovies
}