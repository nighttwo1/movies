package com.nighttwo1.presentation.ui

import androidx.navigation.NavHostController
import com.nighttwo1.domain.model.MovieId
import com.nighttwo1.domain.model.TvId
import kotlinx.serialization.Serializable

class MainViewNavigation(
    val navHostController: NavHostController,
) {
    fun goHome() {
        navHostController.navigate(Home) {
            navHostController.graph.startDestinationRoute?.let {
                popUpTo(it) { saveState = true }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun goUpcoming() {
        navHostController.navigate(Upcoming) {
            navHostController.graph.startDestinationRoute?.let {
                popUpTo(it) { saveState = true }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun goSearch() {
        navHostController.navigate(Search) {
            navHostController.graph.startDestinationRoute?.let {
                popUpTo(it) { saveState = true }
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun goMovieDetail(movieId: MovieId) {
        navHostController.navigate(Movie(id = movieId.value.toString()))
    }

    fun goTvDetail(tvId: TvId) {
        navHostController.navigate(TVSeries(id = tvId.value.toString()))
    }

    fun goBack() {
        navHostController.popBackStack()
    }

    fun isHome(): Boolean {
        return navHostController.currentBackStackEntry?.destination?.route == Home.javaClass.name
    }

//    fun isMovie(): Boolean {
//        return navHostController.currentBackStackEntry?.destination?.route == MainViewNavGraph.Movie.route
//    }
//
//    fun isTVSeries(): Boolean {
//        return navHostController.currentBackStackEntry?.destination?.route == MainViewNavGraph.TVSeries.route
//    }

    fun isUpcoming(): Boolean {
        return navHostController.currentBackStackEntry?.destination?.route == Upcoming.javaClass.name
    }

    fun isSearch(): Boolean {
        return navHostController.currentBackStackEntry?.destination?.route == Search.javaClass.name
    }
}

@Serializable
object Home

@Serializable
object Search

@Serializable
data class Movie(val id: String)

@Serializable
data class TVSeries(val id: String)

@Serializable
object Upcoming
