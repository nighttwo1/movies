package com.nighttwo1.domain.usecase

import com.nighttwo1.domain.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
){
    operator fun invoke(language: String = "ko", region: String = "kr") = movieRepository.getPopularMovie(language, region)
}