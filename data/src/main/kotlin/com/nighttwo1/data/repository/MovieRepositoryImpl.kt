package com.nighttwo1.data.repository

import androidx.paging.PagingData
import com.nighttwo1.data.adapter.networkAdapter
import com.nighttwo1.data.adapter.singlePager
import com.nighttwo1.data.model.toDomain
import com.nighttwo1.data.service.MovieService
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.Movie
import com.nighttwo1.domain.model.MovieAccountStates
import com.nighttwo1.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRepository {
    override fun getNowPlayingMovie(language: String, region: String): Flow<PagingData<Movie>> = singlePager { page ->
        movieService.getNowPlayingMovie(language, page, region).toDomain()
    }

    override fun getPopularMovie(language: String, region: String): Flow<PagingData<Movie>> = singlePager { page ->
        movieService.getPopularMovie(language, page, region).toDomain()
    }

    override fun getMovieDetail(movieId: String, language: String) = networkAdapter {
        (movieService.getMovieDetail(movieId, language) as NetworkResult.Success).data!!.toDomain()
    }

    override suspend fun getMovieAccountStates(movieId: String): NetworkResult<MovieAccountStates> {
        return try {
            NetworkResult.Success(movieService.getMovieAccountStates(movieId).toDomain())
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }
}