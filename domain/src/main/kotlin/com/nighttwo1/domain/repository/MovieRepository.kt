package com.nighttwo1.domain.repository

import androidx.paging.PagingData
import com.nighttwo1.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getNowPlayingMovie(region: String): Flow<PagingData<Movie>>
    fun getPopularMovie(region: String): Flow<PagingData<Movie>>
}