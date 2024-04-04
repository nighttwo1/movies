package com.nighttwo1.data.adapter

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nighttwo1.domain.model.TMDBTVSeries
import com.nighttwo1.domain.model.TVSeries

class TVSinglePageSource(val call: suspend (page: Int) -> TMDBTVSeries): PagingSource<Int, TVSeries>() {
    override fun getRefreshKey(state: PagingState<Int, TVSeries>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVSeries> {
        val next = params.key ?: 1
        return try {
            val data = call(next)
            LoadResult.Page(
                data = data.tvSeriesList,
                prevKey = if (next == 1) null else next - 1,
                nextKey = if (data.totalPages <= next) null else next + 1
            )
        } catch (e: Exception) {
            Log.e("TAG", "ERROR SinglePageSource", e)
            LoadResult.Error(e)
        }
    }
}


private const val perPage: Int = 20

fun tvSinglePager(call: suspend (page: Int) -> TMDBTVSeries) =
    Pager(PagingConfig(pageSize = perPage)) {
        TVSinglePageSource(call)
    }.flow
