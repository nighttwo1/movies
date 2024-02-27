package com.nighttwo1.data.repository

import androidx.paging.PagingData
import com.nighttwo1.data.adapter.singlePager
import com.nighttwo1.data.model.toDomain
import com.nighttwo1.data.service.SearchService
import com.nighttwo1.domain.model.Movie
import com.nighttwo1.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchService: SearchService
) : SearchRepository {
    override fun getSearchedMovie(searchedText: String, language: String, region: String): Flow<PagingData<Movie>> =
        singlePager { page ->
            searchService.getSearchedMovie(searchedText, language, page, region).toDomain()
        }
}