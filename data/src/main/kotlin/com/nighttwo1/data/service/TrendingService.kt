package com.nighttwo1.data.service

import com.nighttwo1.data.model.TMDBTrending
import com.nighttwo1.data.model.TMDBTrendingTV
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TrendingService {
    @GET("trending/movie/{time_window}")
    suspend fun getMovieTrending(
        @Path("time_window") timeWindow: String,
        @Query("language") language: String,
    ): TMDBTrending

    @GET("trending/tv/{time_window}")
    suspend fun getTVSeriesTrending(
        @Path("time_window") timeWindow: String,
        @Query("language") language: String,
    ): TMDBTrendingTV
}