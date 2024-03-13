package com.nighttwo1.presentation.ui.movie

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.nighttwo1.domain.model.Movie
import com.nighttwo1.domain.model.MovieId
import com.nighttwo1.domain.model.Ratings
import com.nighttwo1.presentation.theme.MoviesTheme
import com.nighttwo1.presentation.ui.LocalMainViewNavigation
import kotlinx.coroutines.flow.flowOf
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("PrivateResource")
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MoviesPlaying(
    title: String,
    movies: LazyPagingItems<Movie>
) {
    val mainViewNavigation = LocalMainViewNavigation.current
    val lazyListState = rememberLazyListState()
    val dateFormat = SimpleDateFormat("yyyy-mm-dd")

    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(title, fontSize = 20.sp, fontWeight = FontWeight(700))
        when (movies.loadState.refresh) {
            LoadState.Loading -> {
                CircularProgressIndicator()
            }

            is LoadState.Error -> {
                Button(onClick = { movies.retry() }) {
                    Text("reload")
                }
            }

            else -> {
                LazyRow(
                    state = lazyListState,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(
                        count = movies.itemCount,
                    ) { index ->
                        val item = movies[index] ?: return@items
                        Card(
                            modifier = Modifier.width(106.dp).clickable {
                                mainViewNavigation.goMovieDetail(item.id)
                            },
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Box(
                                modifier = Modifier.fillMaxWidth().height(160.dp),
                                contentAlignment = Alignment.BottomStart
                            ) {
                                GlideImage(
                                    model = "https://image.tmdb.org/t/p/w342/${item.posterPath}",
                                    contentDescription = item.title,
                                    contentScale = ContentScale.FillBounds
                                )
                                Text(
                                    text = "${index + 1}",
                                    fontSize = 40.sp,
                                    fontWeight = FontWeight(800),
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 10.dp),
                                    fontStyle = FontStyle.Italic
                                )
                            }
                            Text(
                                text = item.title,
                                fontSize = 14.sp,
                                fontWeight = FontWeight(500),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = dateFormat.format(item.releaseDate),
                                fontSize = 14.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MoviesPlayingPreview() {
    MoviesTheme {
        MoviesPlaying(
            "Popular",
            flowOf(
                PagingData.from(
                    listOf(
                        Movie(
                            id = MovieId(1),
                            posterPath = "",
                            releaseDate = Date(),
                            title = "Land of BadLand of Bad",
                            rating = Ratings(40.0),
                        )
                    ),
                    sourceLoadStates = LoadStates(
                        //refresh = LoadState.Error(IllegalStateException()),
                        refresh = LoadState.NotLoading(true),
                        prepend = LoadState.NotLoading(true),
                        append = LoadState.NotLoading(true),
                    )
                )
            ).collectAsLazyPagingItems(),
        )
    }
}