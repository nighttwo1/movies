package com.nighttwo1.data.service

import com.nighttwo1.data.model.MovieDetail
import com.nighttwo1.data.model.TMDBMovies
import com.nighttwo1.domain.NetworkResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): TMDBMovies

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String
    ): TMDBMovies

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: String,
        @Query("language") language: String,
    ): NetworkResult<MovieDetail>
}