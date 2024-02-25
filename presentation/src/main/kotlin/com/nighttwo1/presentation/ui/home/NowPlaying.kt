package com.nighttwo1.presentation.ui.home

import android.annotation.SuppressLint
import android.media.Rating
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.nighttwo1.domain.model.Movie
import com.nighttwo1.domain.model.Ratings
import kotlin.math.roundToInt

@SuppressLint("PrivateResource")
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NowPlaying(
    nowPlayings: LazyPagingItems<Movie>
) {
    val lazyListState = rememberLazyListState()

    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Now playings", fontSize = 20.sp, fontWeight = FontWeight(700))
        when (nowPlayings.loadState.refresh) {
            LoadState.Loading -> {
                CircularProgressIndicator()
            }

            is LoadState.Error -> {

            }

            else -> {
                LazyRow(
                    state = lazyListState,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(
                        count = nowPlayings.itemCount,
                    ) { index ->
                        val item = nowPlayings[index] ?: return@items
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Box(
                                contentAlignment = Alignment.BottomStart
                            ) {
                                GlideImage(
                                    model = "https://image.tmdb.org/t/p/w500/${item.posterPath}",
                                    loading = placeholder(
                                        androidx.appcompat.R.drawable.test_level_drawable
                                    ),
                                    contentDescription = item.title
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
                            Text(text = item.title, fontSize = 14.sp, fontWeight = FontWeight(500))
                            Text(text = item.releaseDate, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}
