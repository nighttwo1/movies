package com.nighttwo1.domain.usecase

import com.nighttwo1.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieAccountStatesUseCase  @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId: String) = movieRepository.getMovieAccountStates(movieId)
}