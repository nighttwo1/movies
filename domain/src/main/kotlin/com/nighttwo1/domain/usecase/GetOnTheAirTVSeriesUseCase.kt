package com.nighttwo1.domain.usecase

import com.nighttwo1.domain.repository.TVSeriesRepository
import javax.inject.Inject

class GetOnTheAirTVSeriesUseCase @Inject constructor(
    private val tvSeriesRepository: TVSeriesRepository
) {
    operator fun invoke(language: String = "ko", timeZone: String = "KR") =
        tvSeriesRepository.getOnTheAirTvSeries(language, timeZone)
}
