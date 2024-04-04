package com.nighttwo1.data.repository

import com.nighttwo1.data.adapter.networkAdapter
import com.nighttwo1.data.model.TMDBTrendingTV
import com.nighttwo1.data.model.toDomain
import com.nighttwo1.data.service.TrendingService
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.TMDBTrending
import com.nighttwo1.domain.repository.TrendingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    private val trendingService: TrendingService
) : TrendingRepository {
    override fun getMovieTrending(timeWindow: String, language: String): Flow<NetworkResult<TMDBTrending>> =
        networkAdapter {
            trendingService.getMovieTrending(timeWindow, language).toDomain()
        }

    override fun getTVSeriesTrending(timeWindow: String, language: String): Flow<NetworkResult<TMDBTrending>> =
        networkAdapter {
            trendingService.getTVSeriesTrending(timeWindow, language).toDomain()
        }
}