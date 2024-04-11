package com.nighttwo1.domain.repository

import androidx.paging.PagingData
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.SeasonDetail
import com.nighttwo1.domain.model.TvDetail
import com.nighttwo1.domain.model.TvSeries
import kotlinx.coroutines.flow.Flow

interface TVSeriesRepository {
    fun getOnTheAirTvSeries(language: String, timeZone: String): Flow<PagingData<TvSeries>>
    fun getPopularTvSeries(language: String): Flow<PagingData<TvSeries>>
    fun getTopRatedTvSeries(language: String): Flow<PagingData<TvSeries>>
    fun getTVSeriesDetail(language: String, seriesId: String): Flow<NetworkResult<TvDetail>>
    fun getSeasonDetail(language: String, seriesId: String, seasonNumber: String): Flow<NetworkResult<SeasonDetail>>
}