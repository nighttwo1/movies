package com.nighttwo1.data.service

import com.nighttwo1.data.model.TMDBTVSeries
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVService {
    @GET("tv/on_the_air")
    suspend fun getOnTheAir(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("timezone") timezone: String,
    ): TMDBTVSeries

    @GET("tv/popular")
    suspend fun getPopularTV(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): TMDBTVSeries

    @GET("tv/top_rated")
    suspend fun getTopRatedTV(
        @Query("language") language: String,
        @Query("page") page: Int,
    ): TMDBTVSeries

    @GET("tv/{series_id}")
    suspend fun getTVSeriesDetail(
        @Path("series_id") seriesId: Int,
        @Query("language") language: String,
    )
}