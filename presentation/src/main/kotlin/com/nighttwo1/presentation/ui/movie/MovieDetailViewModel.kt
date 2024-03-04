package com.nighttwo1.presentation.ui.movie

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.MovieAccountStates
import com.nighttwo1.domain.model.MovieDetail
import com.nighttwo1.domain.usecase.GetMovieAccountStatesUseCase
import com.nighttwo1.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: GetMovieDetailUseCase,
    private val movieAccountStatesUseCase: GetMovieAccountStatesUseCase
) : ViewModel() {
    val movieDetailResult: MutableStateFlow<NetworkResult<MovieDetail>> =
        MutableStateFlow(NetworkResult.Ready())

    fun getMovieDetail(movieId: String) = viewModelScope.launch {
        movieDetailUseCase(movieId).collect(movieDetailResult)
    }

    val movieAccountStatesResult = mutableStateOf<NetworkResult<MovieAccountStates>>(NetworkResult.Ready())
    suspend fun getMovieAccountStates(movieId: String) {
        movieAccountStatesResult.value = movieAccountStatesUseCase(movieId)
    }
}