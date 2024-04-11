package com.nighttwo1.data.adapter

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nighttwo1.domain.model.TMDBTvSeries
import com.nighttwo1.domain.model.TvSeries

class TVSinglePageSource(val call: suspend (page: Int) -> TMDBTvSeries): PagingSource<Int, TvSeries>() {
    override fun getRefreshKey(state: PagingState<Int, TvSeries>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvSeries> {
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

fun tvSinglePager(call: suspend (page: Int) -> TMDBTvSeries) =
    Pager(PagingConfig(pageSize = perPage)) {
        TVSinglePageSource(call)
    }.flow
