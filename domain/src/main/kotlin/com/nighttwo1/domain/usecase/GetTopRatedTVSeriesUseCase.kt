package com.nighttwo1.domain.usecase

import com.nighttwo1.domain.repository.TVSeriesRepository
import javax.inject.Inject

class GetTopRatedTVSeriesUseCase @Inject constructor(
    private val tvSeriesRepository: TVSeriesRepository
){
    operator fun invoke(language: String = "ko") = tvSeriesRepository.getPopularTvSeries(language)
}