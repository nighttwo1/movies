package com.nighttwo1.domain.usecase

import com.nighttwo1.domain.repository.TrendingRepository
import javax.inject.Inject

class GetTrendingTVSeriesUseCase @Inject constructor(
    private val trendingRepository: TrendingRepository
) {
    operator fun invoke(timeWindow: String = "day", language: String = "ko") =
        trendingRepository.getTVSeriesTrending(timeWindow, language)
}