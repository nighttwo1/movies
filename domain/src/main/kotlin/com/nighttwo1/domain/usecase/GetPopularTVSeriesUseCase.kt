package com.nighttwo1.domain.usecase

import com.nighttwo1.domain.repository.TVSeriesRepository
import javax.inject.Inject

class GetPopularTVSeriesUseCase @Inject constructor(private val tvSeriesRepository: TVSeriesRepository) {
    operator fun invoke(language: String = "ko") = tvSeriesRepository.getPopularTVSeries(language)
}