package com.nighttwo1.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomePage(
    homeViewModel: HomeViewModel = hiltViewModel()
){
    val nowPlayings = homeViewModel.nowPlayingMovies.collectAsLazyPagingItems()

    Column {
        NowPlaying(nowPlayings)
    }
}