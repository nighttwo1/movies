package com.nighttwo1.presentation.ui.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.MovieDetail
import com.nighttwo1.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {
    val movieDetailResult: MutableStateFlow<NetworkResult<MovieDetail>> =
        MutableStateFlow(NetworkResult.Ready())

    fun getMovieDetail(movieId: String) = viewModelScope.launch {
        movieDetailUseCase(movieId).collect(movieDetailResult)
    }
}