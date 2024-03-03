package com.nighttwo1.presentation.ui.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun MovieScreen(
    movieViewModel: MovieViewModel = hiltViewModel()
) {
    val nowPlayings = movieViewModel.nowPlayingMovies.collectAsLazyPagingItems()
    val popularMovies = movieViewModel.popularMovies.collectAsLazyPagingItems()

    Column {
        MoviesPlaying("Now playings", nowPlayings)
        MoviesPlaying("Popular", popularMovies)
    }
}