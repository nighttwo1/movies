package com.nighttwo1.domain.usecase

import com.nighttwo1.domain.repository.TVSeriesRepository
import javax.inject.Inject

class GetTvSeriesDetailUseCase @Inject constructor(
    private val tvSeriesRepository: TVSeriesRepository
) {
    operator fun invoke(seriesId: String, language: String = "ko") =
        tvSeriesRepository.getTVSeriesDetail(language, seriesId)
}