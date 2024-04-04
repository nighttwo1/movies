package com.nighttwo1.presentation.ui.movie

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.MovieId
import com.nighttwo1.domain.model.TMDBTrending
import com.nighttwo1.domain.model.Trending
import com.nighttwo1.presentation.component.textIconButton.TextIconButton
import com.nighttwo1.presentation.ui.LocalMainViewNavigation

@Composable
fun MovieScreen(
    movieViewModel: MovieViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val nowPlayings = movieViewModel.nowPlayingMovies.collectAsLazyPagingItems()
    val popularMovies = movieViewModel.popularMovies.collectAsLazyPagingItems()
    val topRatedMovies = movieViewModel.topRatedMovies.collectAsLazyPagingItems()
    val trendingMovie by movieViewModel.getTrendingMovie.collectAsState()
    val watchlistResult by movieViewModel.watchlistResult.collectAsState()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .aspectRatio(0.7f)
        ) {
            when (trendingMovie) {
                is NetworkResult.Success -> Trending(
                    trending = (trendingMovie as NetworkResult.Success<TMDBTrending>).data?.trendings?.get(0),
                    setWatchList = movieViewModel::setWatchlist,
                    watchlistResult = watchlistResult
                )

                is NetworkResult.Error -> {}
                is NetworkResult.Loading -> {}
                is NetworkResult.Ready -> {}
            }
        }
        MoviesPlaying("Now playings", nowPlayings)
        MoviesPlaying("Popular", popularMovies)
        MoviesPlaying("Top Rated", topRatedMovies)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BoxScope.Trending(
    trending: Trending?,
    setWatchList: (trendingId: Int) -> Unit,
    watchlistResult: NetworkResult<Boolean>
) {
    val context = LocalContext.current
    val mainViewNavigation = LocalMainViewNavigation.current

    if (watchlistResult is NetworkResult.Error) {
        Toast.makeText(context, "[ERROR] ${watchlistResult.throwable.message}", Toast.LENGTH_SHORT).show()
    }

    GlideImage(
        model = "https://image.tmdb.org/t/p/original${trending?.posterPath}",
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xBF000000),
                        Color.Transparent
                    )
                )
            )
    )

    Column(
        Modifier
            .fillMaxWidth()
            .align(alignment = Alignment.BottomCenter)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        trending?.genres?.let {
            Text(
                text = it.joinToString(" ㆍ ") { type ->
                    type.displayName
                },
                modifier = Modifier.padding(top = 120.dp),
                fontSize = 12.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
        Row(
            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextIconButton(
                onClick = { setWatchList(trending!!.id) },
                enabled = watchlistResult !is NetworkResult.Loading,
                loading = watchlistResult is NetworkResult.Loading,
                prefixIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = "Add") }
            ) {
                Text(text = "My List")
            }
            TextIconButton(
                onClick = { mainViewNavigation.goMovieDetail(MovieId(trending!!.id)) },
                prefixIcon = { Icon(imageVector = Icons.Default.Info, contentDescription = "Info") }
            ) {
                Text(text = "Info")
            }
        }
    }
}