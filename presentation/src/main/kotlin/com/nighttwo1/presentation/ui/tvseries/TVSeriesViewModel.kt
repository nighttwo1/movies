package com.nighttwo1.presentation.ui.tvseries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.Watchlist
import com.nighttwo1.domain.usecase.GetOnTheAirTVSeriesUseCase
import com.nighttwo1.domain.usecase.GetPopularTVSeriesUseCase
import com.nighttwo1.domain.usecase.GetTopRatedTVSeriesUseCase
import com.nighttwo1.domain.usecase.GetTrendingTVSeriesUseCase
import com.nighttwo1.domain.usecase.SetWatchlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVSeriesViewModel @Inject constructor(
    private val getTrendingTVSeriesUseCase: GetTrendingTVSeriesUseCase,
    private val setWatchlistUseCase: SetWatchlistUseCase,
    private val getOnTheAirTVSeriesUseCase: GetOnTheAirTVSeriesUseCase,
    private val getPopularTVSeriesUseCase: GetPopularTVSeriesUseCase,
    private val getTopRatedTVSeriesUseCase: GetTopRatedTVSeriesUseCase
) : ViewModel() {
    val trendingTVSeries =
        getTrendingTVSeriesUseCase().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NetworkResult.Ready())

    val watchlistResult = MutableStateFlow<NetworkResult<Boolean>>(NetworkResult.Ready())
    fun setWatchlist(trendingId: Int) {
        viewModelScope.launch {
            setWatchlistUseCase(
                Watchlist("movie", trendingId, true)
            ).collect(watchlistResult)
        }
    }

    val onTheAirTVSeries = getOnTheAirTVSeriesUseCase().cachedIn(viewModelScope)
    val popularTVSeries = getPopularTVSeriesUseCase().cachedIn(viewModelScope)
    val topRatedTVSeries = getTopRatedTVSeriesUseCase().cachedIn(viewModelScope)
}