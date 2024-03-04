package com.nighttwo1.domain.repository

import androidx.paging.PagingData
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.Movie
import com.nighttwo1.domain.model.MovieAccountStates
import com.nighttwo1.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getNowPlayingMovie(language: String, region: String): Flow<PagingData<Movie>>
    fun getPopularMovie(language: String, region: String): Flow<PagingData<Movie>>
    fun getMovieDetail(movieId: String, language: String): Flow<NetworkResult<MovieDetail>>

    suspend fun getMovieAccountStates(movieId: String): NetworkResult<MovieAccountStates>
}