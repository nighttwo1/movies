package com.nighttwo1.domain.repository

import androidx.paging.PagingData
import com.nighttwo1.domain.model.TVSeries
import kotlinx.coroutines.flow.Flow
import org.intellij.lang.annotations.Language

interface TVSeriesRepository {
    fun getOnTheAirTVSeries(language: String, timeZone: String): Flow<PagingData<TVSeries>>
    fun getPopularTVSeries(language: String): Flow<PagingData<TVSeries>>
    fun getTopRatedTVSeries(language: String): Flow<PagingData<TVSeries>>
}