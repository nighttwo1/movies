package com.nighttwo1.domain.usecase

import com.nighttwo1.domain.model.MovieId
import com.nighttwo1.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieCreditsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: String, language: String = "ko") =
        movieRepository.getMovieCredits(movieId = movieId, language = language)
}