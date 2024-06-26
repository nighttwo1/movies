package com.nighttwo1.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nighttwo1.presentation.component.bottomNavigationBar.BottomNavigationBar
import com.nighttwo1.presentation.theme.MoviesTheme
import com.nighttwo1.presentation.ui.home.HomeScreen
import com.nighttwo1.presentation.ui.movie.MovieDetail
import com.nighttwo1.presentation.ui.search.SearchScreen
import com.nighttwo1.presentation.ui.tvseries.TvDetail
import com.nighttwo1.presentation.ui.upcoming.UpcomingScreen

@Composable
fun RootScreen() {
    val mainNavigation = rememberMainNavigation()

    MoviesTheme {
        CompositionLocalProvider(
            LocalMainViewNavigation provides mainNavigation,
        ) {
            Scaffold(
                bottomBar = { BottomNavigationBar() }
            ) {
                Box(
                    modifier = Modifier
                        .padding(it)
                ) {
                    MainViewNavigation()
                }
            }
        }
    }
}

@Composable
fun MainViewNavigation() {
    NavHost(MoviesAppNavigation.mainViewNavigation.navHostController, startDestination = Home) {
        composable<Home>{
            HomeScreen()
        }

        composable<Upcoming> {
            UpcomingScreen()
        }

        composable<Search> {
            SearchScreen()
        }

        composable<Movie> { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("id")
            movieId?.let { id ->
                MovieDetail(movieId = id)
            }
        }

        composable<TVSeries> { backStackEntry ->
            val tvId = backStackEntry.arguments?.getString("id")
            tvId?.let { id ->
                TvDetail(tvId = id)
            }
        }
    }
}

val LocalMainViewNavigation = staticCompositionLocalOf<MainViewNavigation> {
    error("No NavHostController provided")
}

@Composable
fun rememberMainNavigation(navController: NavHostController = rememberNavController()) =
    remember { MainViewNavigation(navController) }

object MoviesAppNavigation {
    val mainViewNavigation: MainViewNavigation
        @Composable
        get() = LocalMainViewNavigation.current
}
