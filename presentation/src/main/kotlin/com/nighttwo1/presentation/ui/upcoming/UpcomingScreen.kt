package com.nighttwo1.presentation.ui.upcoming

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.nighttwo1.domain.model.Movie
import com.nighttwo1.presentation.R
import com.nighttwo1.presentation.ui.LocalMainViewNavigation
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun UpcomingScreen(
    upcomingViewModel: UpcomingViewModel = hiltViewModel()
) {
    val mainViewNavigation = LocalMainViewNavigation.current
    val upcomingMovies = upcomingViewModel.upcomingMovies.collectAsLazyPagingItems()
    val lazyGridState = rememberLazyGridState()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedMovie by remember { mutableStateOf<Movie?>(null) }
    val dateFormat = SimpleDateFormat("yyyy")

    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Upcoming movies", fontSize = 20.sp, fontWeight = FontWeight(700))
        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            state = lazyGridState,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(
                count = upcomingMovies.itemCount,
                key = { upcomingMovies[it]?.id?.value ?: 0 }
            ) { index ->
                val item = upcomingMovies[index] ?: return@items
                Card(
                    modifier = Modifier.width(106.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Transparent
                    ),
                    onClick = {
                        selectedMovie = item
                        showBottomSheet = true
                    }
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(160.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        GlideImage(
                            model = "https://image.tmdb.org/t/p/w342/${item.posterPath}",
                            loading = placeholder(R.drawable.poster_card),
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
                }
            }
        }
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                        selectedMovie = null
                    }
                }
            },
        ) {
            if(selectedMovie == null){
                Text("Something went wrong")
            }else {
                Row(
                    modifier = Modifier.padding(10.dp, 0.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Card(
                        modifier = Modifier.width(106.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                    ) {
                        Box(
                            modifier = Modifier.width(120.dp).height(160.dp),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            GlideImage(
                                model = "https://image.tmdb.org/t/p/w342/${selectedMovie!!.posterPath}",
                                loading = placeholder(R.drawable.poster_card),
                                contentDescription = selectedMovie?.title,
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                    Column {
                        Text(
                            text = selectedMovie!!.title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = dateFormat.format(selectedMovie!!.releaseDate),
                            fontSize = 14.sp,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = selectedMovie!!.overview,
                            fontSize = 14.sp,
                            maxLines = 5,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Button(onClick = {
                    showBottomSheet = false
                    mainViewNavigation.goMovieDetail(selectedMovie!!.id)
                }, modifier = Modifier.padding(10.dp, 4.dp).fillMaxWidth()){
                    Icon(Icons.Filled.Info, contentDescription = "Info")
                    Text("Get more details")
                }
            }
        }
    }
}
