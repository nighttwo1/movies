package com.nighttwo1.domain.repository

import androidx.paging.PagingData
import com.nighttwo1.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getNowPlayingMovie(language: String, region: String): Flow<PagingData<Movie>>
    fun getPopularMovie(language: String, region: String): Flow<PagingData<Movie>>
}