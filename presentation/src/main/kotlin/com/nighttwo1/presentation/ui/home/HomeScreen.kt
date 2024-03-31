package com.nighttwo1.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nighttwo1.presentation.res.vector.MyIconPack
import com.nighttwo1.presentation.res.vector.myiconpack.IcLogo
import com.nighttwo1.presentation.theme.MoviesTheme
import com.nighttwo1.presentation.ui.LocalMainViewNavigation
import com.nighttwo1.presentation.ui.movie.MovieScreen

@Composable
fun HomeScreen() {
    val mainViewNavigation = LocalMainViewNavigation.current
    var currentView by remember { mutableStateOf(CurrentView.MOVIES) }

    Box {
        Column(modifier = Modifier.zIndex(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    MyIconPack.IcLogo,
                    contentDescription = "Logo",
                    modifier = Modifier.size(32.dp),
                    tint = Color.Unspecified
                )
                IconButton(onClick = { mainViewNavigation.goSearch() }) {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                TextButton(onClick = { currentView = CurrentView.MOVIES }) {
                    Text("Movies", color = Color.White)
                }
                TextButton(onClick = { currentView = CurrentView.TV_SERIES }) {
                    Text("TV Series", color = Color.White)
                }
            }
        }
        when (currentView) {
            CurrentView.MOVIES -> MovieScreen()
            else -> Text("tv")
        }
    }
}

enum class CurrentView {
    MOVIES, TV_SERIES
}

@Preview
@Composable
fun HomeScreenPreview() {
    MoviesTheme {
        HomeScreen()
    }
}