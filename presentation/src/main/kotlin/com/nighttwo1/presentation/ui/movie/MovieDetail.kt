package com.nighttwo1.presentation.ui.movie

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.nighttwo1.domain.NetworkResult
import com.nighttwo1.domain.model.GenreId
import com.nighttwo1.domain.model.Genres
import com.nighttwo1.domain.model.MovieAccountStates
import com.nighttwo1.domain.model.MovieDetail
import com.nighttwo1.domain.model.MovieId
import com.nighttwo1.domain.model.Ratings
import com.nighttwo1.presentation.R
import com.nighttwo1.presentation.res.vector.MyIconPack
import com.nighttwo1.presentation.res.vector.myiconpack.IcFavoriteBorder
import com.nighttwo1.presentation.ui.LocalMainViewNavigation
import java.text.SimpleDateFormat
import java.time.Duration

@Composable
fun MovieDetail(
    movieDetailViewModel: MovieDetailViewModel = hiltViewModel(),
    movieId: String?
) {
    val mainViewNavigation = LocalMainViewNavigation.current
    val movie by movieDetailViewModel.movieDetailResult.collectAsState()
    val movieAccountStatesResult by movieDetailViewModel.movieAccountStatesResult

    LaunchedEffect(Unit) {
        movieDetailViewModel.getMovieDetail(movieId ?: "")
        movieDetailViewModel.getMovieAccountStates(movieId ?: "")
    }

    Box {
        when (movie) {
            is NetworkResult.Ready,
            is NetworkResult.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is NetworkResult.Error -> {
                Button(onClick = {
                    movieDetailViewModel.getMovieDetail(movieId ?: "")
                }) {
                    Text("reload")
                }
            }

            is NetworkResult.Success -> {
                (movie as NetworkResult.Success<MovieDetail>).data?.let { MovieDetail(it, movieAccountStatesResult) }
            }
        }
        IconButton(
            onClick = { mainViewNavigation.goBack() }
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "back", tint = Color.White)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun MovieDetail(
    movie: MovieDetail,
    movieAccountStatesResult: NetworkResult<MovieAccountStates>
) {
    val dateFormat = SimpleDateFormat("yyyy")
    val scrollState = rememberScrollState()

    val favorite by remember {
        mutableStateOf(
            if (movieAccountStatesResult is NetworkResult.Success) {
                movieAccountStatesResult.data!!.favorite
            } else false
        )
    }
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.BottomStart
        ) {
            GlideImage(
                model = "https://image.tmdb.org/t/p/original/${movie.backdropPath}",
                loading = placeholder(R.drawable.backdrop_poster),
                contentDescription = movie.title,
                contentScale = ContentScale.FillWidth
            )
        }
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = movie.title, fontWeight = FontWeight(600), fontSize = 24.sp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier.border(
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(4.dp)
                    )
                        .padding(2.dp)
                ) {
                    Text(dateFormat.format(movie.releaseDate), fontWeight = FontWeight(500))
                }
                Text("${movie.runtime.toMinutes().toInt()} min", fontWeight = FontWeight(500))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Star",
                        tint = Color.Yellow,
                        modifier = Modifier.width(16.dp)
                    )
                    Text(text = movie.rating.toString(), fontWeight = FontWeight(500))
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Column(
                    modifier = Modifier.width(80.dp).clip(shape = RoundedCornerShape(4.dp)).clickable {

                    }.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Icon(
                        imageVector = if (favorite) Icons.Filled.Favorite else MyIconPack.IcFavoriteBorder,
                        contentDescription = "Favorite",
                        modifier = Modifier.size(24.dp)
                    )
                    Text("Favorite", fontWeight = FontWeight(400), fontSize = 10.sp, color = Color.Gray)
                }
                Column(
                    modifier = Modifier.width(80.dp).clip(shape = RoundedCornerShape(4.dp)).clickable {

                    }.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "WatchList",
                        modifier = Modifier.size(24.dp)
                    )
                    Text("Watch List", fontWeight = FontWeight(400), fontSize = 10.sp, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = movie.overview, fontWeight = FontWeight(400), fontSize = 12.sp)
        }
    }
}

@Preview
@Composable
fun MovieDetailPreview() {
    val dateFormat = SimpleDateFormat("yyyy-mm-dd")
    MovieDetail(
        movie = MovieDetail(
            id = MovieId(969492),
            genres = listOf(Genres(GenreId(28), "Action"), Genres(GenreId(53), "Thriller")),
            backdropPath = "/oBIQDKcqNxKckjugtmzpIIOgoc4.jpg",
            posterPath = "/h3jYanWMEJq6JJsCopy1h7cT2Hs.jpg",
            releaseDate = dateFormat.parse("2024-01-25")!!,
            title = "Land of Bad",
            overview = "When a Delta Force special ops mission goes terribly wrong, Air Force drone pilot Reaper has 48 hours to remedy what has devolved into a wild rescue operation. With no weapons and no communication other than the drone above, the ground mission suddenly becomes a full-scale battle when the team is discovered by the enemy.",
            rating = Ratings(7.0232),
            runtime = Duration.ofMinutes(113),
        ),
        NetworkResult.Ready()
    )
}
