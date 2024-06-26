package com.nighttwo1.presentation.ui.movie

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.Favorite
import com.nighttwo1.domain.model.MovieAccountStates
import com.nighttwo1.domain.model.MovieCredits
import com.nighttwo1.domain.model.MovieDetail
import com.nighttwo1.domain.usecase.GetMovieAccountStatesUseCase
import com.nighttwo1.domain.usecase.GetMovieCreditsUseCase
import com.nighttwo1.domain.usecase.GetMovieDetailUseCase
import com.nighttwo1.domain.usecase.SetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: GetMovieDetailUseCase,
    private val movieAccountStatesUseCase: GetMovieAccountStatesUseCase,
    private val movieCreditsUseCase: GetMovieCreditsUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase
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

    val movieCreditsResult = mutableStateOf<NetworkResult<MovieCredits>>(NetworkResult.Ready())
    suspend fun getMovieCredits(movieId: String) {
        movieCreditsResult.value = movieCreditsUseCase(movieId)
    }

    val movieFavoriteResult = mutableStateOf<NetworkResult<Boolean>>(NetworkResult.Ready())
    fun setFavorite(movieId: String, enable: Boolean) {
        viewModelScope.launch {
            movieFavoriteResult.value = NetworkResult.Ready()
            movieFavoriteResult.value = setFavoriteUseCase(Favorite(type = "movie", id = movieId.toInt(), favorite = enable))
        }
    }
}