package com.nighttwo1.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.nighttwo1.domain.model.Movie
import com.nighttwo1.domain.usecase.GetSearchedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchedMoviesUseCase: GetSearchedMoviesUseCase
) : ViewModel() {
    val searchedMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())

    fun getSearchedMovies(searchedText: String) {
        viewModelScope.launch {
            getSearchedMoviesUseCase(searchedText).cachedIn(viewModelScope).collect(searchedMovies)
        }
    }
}