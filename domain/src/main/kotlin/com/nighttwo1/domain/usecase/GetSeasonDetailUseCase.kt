package com.nighttwo1.domain.usecase

import com.nighttwo1.domain.repository.TVSeriesRepository
import javax.inject.Inject

class GetSeasonDetailUseCase @Inject constructor(
    private val tvSeriesRepository: TVSeriesRepository
) {
    operator fun invoke(language: String = "en-US", seriesId: String, seasonNumber: String) =
        tvSeriesRepository.getSeasonDetail(language, seriesId, seasonNumber)
}