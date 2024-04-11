package com.nighttwo1.data.repository

import androidx.paging.PagingData
import com.nighttwo1.data.adapter.networkAdapter
import com.nighttwo1.data.adapter.tvSinglePager
import com.nighttwo1.data.model.toDomain
import com.nighttwo1.data.service.TvService
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.SeasonDetail
import com.nighttwo1.domain.model.TvDetail
import com.nighttwo1.domain.model.TvSeries
import com.nighttwo1.domain.repository.TVSeriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TVSeriesRepositoryImpl @Inject constructor(
    private val tvService: TvService
) : TVSeriesRepository {
    override fun getOnTheAirTvSeries(language: String, timeZone: String): Flow<PagingData<TvSeries>> =
        tvSinglePager { page ->
            tvService.getOnTheAir(language, page, timeZone).toDomain()
        }

    override fun getPopularTvSeries(language: String): Flow<PagingData<TvSeries>> = tvSinglePager { page ->
        tvService.getPopularTV(language, page).toDomain()
    }

    override fun getTopRatedTvSeries(language: String): Flow<PagingData<TvSeries>> = tvSinglePager { page ->
        tvService.getTopRatedTV(language, page).toDomain()
    }

    override fun getTVSeriesDetail(language: String, seriesId: String): Flow<NetworkResult<TvDetail>> = networkAdapter {
        tvService.getTVSeriesDetail(seriesId = seriesId, language = language).toDomain()
    }

    override fun getSeasonDetail(
        language: String,
        seriesId: String,
        seasonNumber: String
    ): Flow<NetworkResult<SeasonDetail>> = networkAdapter {
        tvService.getTVSeasonDetail(seriesId, seasonNumber, language).toDomain()
    }
}