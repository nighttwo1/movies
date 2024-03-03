package com.nighttwo1.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.nighttwo1.presentation.ui.movie.MoviesPlaying

@Composable
fun HomePage(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val nowPlayings = homeViewModel.nowPlayingMovies.collectAsLazyPagingItems()
    val popularMovies = homeViewModel.popularMovies.collectAsLazyPagingItems()

    Column {
        MoviesPlaying("Now playings", nowPlayings)
        MoviesPlaying("Popular", popularMovies)
    }
}