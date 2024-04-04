package com.nighttwo1.presentation.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nighttwo1.presentation.res.vector.MyIconPack
import com.nighttwo1.presentation.res.vector.myiconpack.IcLogo
import com.nighttwo1.presentation.theme.MoviesTheme
import com.nighttwo1.presentation.ui.LocalMainViewNavigation
import com.nighttwo1.presentation.ui.movie.MovieScreen
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    val mainViewNavigation = LocalMainViewNavigation.current
    val pagerState = rememberPagerState(0, 0f, pageCount = { PagerView.entries.size })
    val scope = rememberCoroutineScope()

    val logoAppBarHeight = 50.dp
    val logoAppBarHeightPx = with(LocalDensity.current) { logoAppBarHeight.toPx() }
    val topAppBarHeight = 40.dp
    val topAppBarHeightPx = with(LocalDensity.current) { topAppBarHeight.roundToPx().toFloat() }
    val topAppBarOffsetHeightPx = remember { mutableStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = topAppBarOffsetHeightPx.value + delta
                topAppBarOffsetHeightPx.value = newOffset.coerceIn(-topAppBarHeightPx, 0f)

                return Offset.Zero
            }
        }
    }

    Scaffold(
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().height(logoAppBarHeight).background(Color.Black).padding(5.dp)
                    .zIndex(1f),
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
                modifier = Modifier.fillMaxWidth().offset {
                    IntOffset(
                        x = 0, y = topAppBarOffsetHeightPx.value.roundToInt() + logoAppBarHeightPx.toInt()
                    )
                },
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(PagerView.MOVIES.ordinal)
                    }
                }) {
                    Text(
                        "Movies",
                        color = if (pagerState.currentPage == PagerView.MOVIES.ordinal) Color.White else Color.Gray
                    )
                }
                TextButton(onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(PagerView.TV_SERIES.ordinal)
                    }
                }) {
                    Text(
                        "TV Series",
                        color = if (pagerState.currentPage == PagerView.TV_SERIES.ordinal) Color.White else Color.Gray
                    )
                }
            }
        }
    ) {
        HorizontalPager(
            state = pagerState
        ) { page ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                when (page) {
                    0 -> MovieScreen()
                    else -> Text("tv")
                }
            }
        }
    }
}

enum class PagerView {
    MOVIES, TV_SERIES
}

@Preview
@Composable
fun HomeScreenPreview() {
    MoviesTheme {
        HomeScreen()
    }
}