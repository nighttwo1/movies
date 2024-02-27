package com.nighttwo1.data.repository

import androidx.paging.PagingData
import com.nighttwo1.data.adapter.singlePager
import com.nighttwo1.data.model.toDomain
import com.nighttwo1.data.service.MovieService
import com.nighttwo1.domain.model.Movie
import com.nighttwo1.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRepository {
    override fun getNowPlayingMovie(region: String): Flow<PagingData<Movie>> = singlePager { page ->
        movieService.getNowPlayingMovie(page, region).toDomain()
    }

    override fun getPopularMovie(region: String): Flow<PagingData<Movie>> = singlePager { page ->
        movieService.getPopularMovie(page, region).toDomain()
    }
}