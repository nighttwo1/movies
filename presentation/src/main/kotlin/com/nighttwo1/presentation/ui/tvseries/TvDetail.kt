package com.nighttwo1.presentation.ui.tvseries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.nighttwo1.domain.model.Episode
import com.nighttwo1.domain.model.EpisodeId
import com.nighttwo1.domain.model.SeasonDetail
import com.nighttwo1.domain.model.TvDetail
import com.nighttwo1.domain.model.TvId
import com.nighttwo1.presentation.R
import com.nighttwo1.presentation.ui.LocalMainViewNavigation
import kotlinx.coroutines.Job
import java.text.SimpleDateFormat
import java.time.Duration

@Composable
fun TvDetail(
    viewModel: TvDetailViewModel = hiltViewModel(),
    tvId: String?
) {
    val mainViewNavigation = LocalMainViewNavigation.current
    val tvDetail by viewModel.tvDetailResult.collectAsState()
    val seasonDetail by viewModel.seasonDetailResult.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getTvDetail(tvId ?: "")
    }

    Box {
        when (tvDetail) {
            is NetworkResult.Ready,
            is NetworkResult.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is NetworkResult.Error -> {
                Button(onClick = {
                    viewModel.getTvDetail(tvId ?: "")
                }) {
                    Text("reload")
                }
            }

            is NetworkResult.Success -> {
                (tvDetail as NetworkResult.Success<TvDetail>).data?.let {
                    TvDetail(
                        tvId ?: "",
                        it,
                        viewModel::getSeasonDetail,
                        seasonDetail,
                    )
                }
            }
        }
        IconButton(
            onClick = { mainViewNavigation.goBack() }
        ) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "back", tint = Color.White)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun TvDetail(
    tvId: String,
    tvDetail: TvDetail,
    getSeasonDetail: (TvId, Int) -> Job,
    seasonDetail: NetworkResult<SeasonDetail>
) {
    val scrollState = rememberScrollState()
    var expanded by remember { mutableStateOf(false) }
    var selectedSeason by remember { mutableStateOf(if (tvDetail.seasons.isNotEmpty()) tvDetail.seasons[0] else null) }

    LaunchedEffect(selectedSeason) {
        if (selectedSeason != null) {
            getSeasonDetail(tvDetail.id, selectedSeason!!.seasonNumber)
        }
    }

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Card {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomStart,
            ) {
                GlideImage(
                    model = "https://image.tmdb.org/t/p/original/${tvDetail.backdropPath}",
                    loading = placeholder(R.drawable.backdrop_poster),
                    contentDescription = tvDetail.name,
                    contentScale = ContentScale.FillWidth
                )
            }
        }
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = tvDetail.name, fontWeight = FontWeight(600), fontSize = 24.sp)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
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
                    Text(text = tvDetail.rating.toString(), fontWeight = FontWeight(500))
                }
            }

            Column(
                modifier = Modifier.padding(top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text("Seasons", fontWeight = FontWeight(500))
                if (tvDetail.seasons.isNotEmpty() && selectedSeason != null) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.menuAnchor().fillMaxWidth(),
                            value = selectedSeason!!.name,
                            onValueChange = {},
                            readOnly = true,
                            singleLine = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.exposedDropdownSize()
                        ) {
                            tvDetail.seasons.map { season ->
                                DropdownMenuItem(
                                    text = { Text(season.name, style = MaterialTheme.typography.bodyLarge) },
                                    onClick = {
                                        selectedSeason = season
                                        expanded = false
                                    },
                                )
                            }
                        }
                    }
                }
                if (seasonDetail is NetworkResult.Success) {
                    seasonDetail.data?.episodes?.map { episode ->
                        EpisodeCard(episode)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun EpisodeCard(episode: Episode) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 5.dp).height(100.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.width(96.dp).height(60.dp),
            contentAlignment = Alignment.Center
        ) {
            GlideImage(
                model = "https://image.tmdb.org/t/p/original/${episode.stillPath}",
                loading = placeholder(R.drawable.tv_episode_placeholder_gray),
                contentDescription = episode.name,
                contentScale = ContentScale.FillWidth
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Episode ${episode.episodeNumber}: ${episode.name}", fontWeight = FontWeight(500))
            Text("${episode.runtime.toMinutes().toInt()} min", fontWeight = FontWeight(400), fontSize = 10.sp)
            Text(episode.overview, fontWeight = FontWeight(400), fontSize = 10.sp)
        }
    }
}


@Preview
@Composable
fun EpisodeCardPreview() {
    val dateFormatter = SimpleDateFormat("yyyy-mm-dd")
    val episode = Episode(
        id = EpisodeId(64330),
        name = "시즌 1",
        overview = "",
        episodeNumber = 99,
        airDate = dateFormatter.parse("2014-02-25"),
        runtime = Duration.ofMinutes(100),
        stillPath = "/6aTObv741nQNeIrNhevVw3OlVQw.jpg"
    )

    EpisodeCard(episode = episode)
}