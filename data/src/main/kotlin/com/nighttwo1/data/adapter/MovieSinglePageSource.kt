package com.nighttwo1.data.adapter

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nighttwo1.domain.model.Movie
import com.nighttwo1.domain.model.TMDBMovies

class MovieSinglePageSource(val call: suspend (page: Int) -> TMDBMovies) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val next = params.key ?: 1
        return try {
            val data = call(next)
            LoadResult.Page(
                data = data.movieList,
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

fun movieSinglePager(call: suspend (page: Int) -> TMDBMovies) =
    Pager(PagingConfig(pageSize = perPage)) {
        MovieSinglePageSource(call)
    }.flow
