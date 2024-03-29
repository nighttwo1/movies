package com.nighttwo1.presentation.ui.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun MovieScreen(
    movieViewModel: MovieViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val nowPlayings = movieViewModel.nowPlayingMovies.collectAsLazyPagingItems()
    val popularMovies = movieViewModel.popularMovies.collectAsLazyPagingItems()
    val topRatedMovies = movieViewModel.topRatedMovies.collectAsLazyPagingItems()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        MoviesPlaying("Now playings", nowPlayings)
        MoviesPlaying("Popular", popularMovies)
        MoviesPlaying("Top Rated", topRatedMovies)
    }
}