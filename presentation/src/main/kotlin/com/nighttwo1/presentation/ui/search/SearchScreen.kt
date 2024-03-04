package com.nighttwo1.presentation.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.nighttwo1.presentation.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun SearchScreen(searchViewModel: SearchViewModel = hiltViewModel()) {
    var text by remember { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    val searchedMovies = searchViewModel.searchedMovies.collectAsLazyPagingItems()
    val lazyGridState = rememberLazyGridState()

    Column {
        SearchBar(
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                active = false
                searchViewModel.getSearchedMovies(it)
            },
            active = active,
            onActiveChange = { active = it },
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            placeholder = { Text("Search movies") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
        ) {}
        Box(
            modifier = Modifier.padding(10.dp).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (searchedMovies.itemCount == 0) {
                Text("No movies found")
            } else {
                when (searchedMovies.loadState.refresh) {
                    LoadState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is LoadState.Error -> {
                        Button(onClick = { searchedMovies.retry() }) {
                            Text("reload")
                        }
                    }

                    else -> {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(100.dp),
                            state = lazyGridState,
                            verticalArrangement = Arrangement.spacedBy(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                        ) {
                            items(
                                count = searchedMovies.itemCount,
                                key = { searchedMovies[it]?.id?.value ?: 0 }
                            ) { index ->
                                val item = searchedMovies[index] ?: return@items
                                Card(
                                    modifier = Modifier.width(106.dp),
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
                                    Text(
                                        text = item.title,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight(500),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}