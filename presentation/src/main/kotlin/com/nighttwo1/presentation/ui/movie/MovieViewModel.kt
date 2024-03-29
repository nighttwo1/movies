package com.nighttwo1.presentation.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nighttwo1.domain.usecase.GetNowPlayingMoviesUseCase
import com.nighttwo1.domain.usecase.GetPopularMoviesUseCase
import com.nighttwo1.domain.usecase.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
): ViewModel() {
    val nowPlayingMovies = getNowPlayingMoviesUseCase().cachedIn(viewModelScope)
    val popularMovies = getPopularMoviesUseCase().cachedIn(viewModelScope)
    val topRatedMovies = getTopRatedMoviesUseCase().cachedIn(viewModelScope)
}