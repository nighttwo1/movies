package com.nighttwo1.presentation.ui.tvseries

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.TMDBTrending
import com.nighttwo1.presentation.ui.movie.Trending

@Composable
fun TVSeriesScreen(viewModel: TVSeriesViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    val trending by viewModel.trendingTVSeries.collectAsStateWithLifecycle()
    val watchlistResult by viewModel.watchlistResult.collectAsStateWithLifecycle()
    val onTheAirTVSeries = viewModel.onTheAirTVSeries.collectAsLazyPagingItems()
    val popularTVSeries = viewModel.popularTVSeries.collectAsLazyPagingItems()
    val topRatedTVSeries = viewModel.topRatedTVSeries.collectAsLazyPagingItems()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(0.7f)
        ) {
            when (trending) {
                is NetworkResult.Success -> Trending(
                    trending = (trending as NetworkResult.Success<TMDBTrending>).data?.trendings?.get(0),
                    setWatchList = viewModel::setWatchlist,
                    watchlistResult = watchlistResult
                )

                is NetworkResult.Error -> {}
                is NetworkResult.Loading -> CircularProgressIndicator()
                is NetworkResult.Ready -> {}
            }
        }
        TVSeriesPlaying("On The Air", onTheAirTVSeries)
        TVSeriesPlaying("Popular", popularTVSeries)
        TVSeriesPlaying("Top Rated", topRatedTVSeries)
    }
}