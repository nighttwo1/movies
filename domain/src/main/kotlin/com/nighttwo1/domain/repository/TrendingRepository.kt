package com.nighttwo1.domain.repository

import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.TMDBTrending
import kotlinx.coroutines.flow.Flow

interface TrendingRepository {
    fun getMovieTrending(timeWindow: String, language: String): Flow<NetworkResult<TMDBTrending>>
    fun getTVSeriesTrending(timeWindow: String, language: String): Flow<NetworkResult<TMDBTrending>>
}