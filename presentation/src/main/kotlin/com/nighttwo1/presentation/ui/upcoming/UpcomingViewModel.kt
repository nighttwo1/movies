package com.nighttwo1.presentation.ui.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nighttwo1.domain.usecase.GetUpcomingMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(
    private val getUpcomingMovieUseCase: GetUpcomingMovieUseCase
) : ViewModel() {
    val upcomingMovies = getUpcomingMovieUseCase().cachedIn(viewModelScope)
}