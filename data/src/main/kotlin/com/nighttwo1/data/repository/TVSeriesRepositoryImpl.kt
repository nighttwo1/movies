package com.nighttwo1.data.repository

import androidx.paging.PagingData
import com.nighttwo1.data.adapter.tvSinglePager
import com.nighttwo1.data.model.toDomain
import com.nighttwo1.data.service.TVService
import com.nighttwo1.domain.model.TVSeries
import com.nighttwo1.domain.repository.TVSeriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TVSeriesRepositoryImpl @Inject constructor(
    private val tvService: TVService
) : TVSeriesRepository {
    override fun getOnTheAirTVSeries(language: String, timeZone: String): Flow<PagingData<TVSeries>> =
        tvSinglePager { page ->
            tvService.getOnTheAir(language, page, timeZone).toDomain()
        }

    override fun getPopularTVSeries(language: String): Flow<PagingData<TVSeries>> = tvSinglePager { page ->
        tvService.getPopularTV(language, page).toDomain()
    }

    override fun getTopRatedTVSeries(language: String): Flow<PagingData<TVSeries>> = tvSinglePager { page ->
        tvService.getTopRatedTV(language, page).toDomain()
    }
}