package com.nighttwo1.domain.repository

import androidx.paging.PagingData
import com.nighttwo1.domain.model.Movie
import kotlinx.coroutines.flow.Flow


interface SearchRepository {
    fun getSearchedMovie(searchedText: String, language: String, region: String): Flow<PagingData<Movie>>
}