package com.nighttwo1.domain.usecase

import com.nighttwo1.domain.repository.SearchRepository
import javax.inject.Inject

class GetSearchedMoviesUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(searchedText: String, language: String = "ko", region: String = "kr") =
        searchRepository.getSearchedMovie(searchedText, language, region)
}