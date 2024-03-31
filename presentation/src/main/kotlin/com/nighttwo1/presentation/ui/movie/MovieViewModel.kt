package com.nighttwo1.presentation.ui.movie

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.Watchlist
import com.nighttwo1.domain.usecase.GetNowPlayingMoviesUseCase
import com.nighttwo1.domain.usecase.GetPopularMoviesUseCase
import com.nighttwo1.domain.usecase.GetTopRatedMoviesUseCase
import com.nighttwo1.domain.usecase.GetTrendingMovieUseCase
import com.nighttwo1.domain.usecase.SetWatchlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getTrendingMovieUseCase: GetTrendingMovieUseCase,
    private val setWatchlistUseCase: SetWatchlistUseCase
) : ViewModel() {
    val nowPlayingMovies = getNowPlayingMoviesUseCase().cachedIn(viewModelScope)
    val popularMovies = getPopularMoviesUseCase().cachedIn(viewModelScope)
    val topRatedMovies = getTopRatedMoviesUseCase().cachedIn(viewModelScope)
    val getTrendingMovie = getTrendingMovieUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), NetworkResult.Ready())

    val watchlistResult = MutableStateFlow<NetworkResult<Boolean>>(NetworkResult.Ready())
    fun setWatchlist(watchlist: Watchlist) {
        viewModelScope.launch {
            setWatchlistUseCase(watchlist).collect(watchlistResult)
        }
    }
}