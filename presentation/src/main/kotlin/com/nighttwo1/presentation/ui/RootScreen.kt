package com.nighttwo1.presentation.ui

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.nighttwo1.presentation.theme.MoviesTheme

@Composable
fun RootScreen(rootViewModel: RootViewModel = hiltViewModel()) {
    val lazyListState = rememberLazyListState()
    val nowPlayings = rootViewModel.nowPlayingMovies.collectAsLazyPagingItems()

    MoviesTheme {
        when (nowPlayings.loadState.refresh) {
            LoadState.Loading -> {
                CircularProgressIndicator()
            }
            is LoadState.Error -> {

            }
            else -> {
                LazyColumn(
                    state = lazyListState
                ) {
                    items(
                        count = nowPlayings.itemCount,
                    ) { index ->
                        nowPlayings[index]?.title?.let { Text(it) }
                    }
                }
            }
        }
    }
}