package com.nighttwo1.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nighttwo1.domain.usecase.GetNowPlayingMoviesUseCase
import com.nighttwo1.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
): ViewModel() {
    val nowPlayingMovies = getNowPlayingMoviesUseCase().cachedIn(viewModelScope)
    val popularMovies = getPopularMoviesUseCase().cachedIn(viewModelScope)
}